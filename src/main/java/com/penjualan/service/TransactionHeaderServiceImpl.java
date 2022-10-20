package com.penjualan.service;

import com.penjualan.dto.KeranjangDTO;
import com.penjualan.dto.ProductGridDTO;
import com.penjualan.dto.TransactionDTO;
import com.penjualan.entity.Login;
import com.penjualan.entity.Product;
import com.penjualan.entity.TransactionDetail;
import com.penjualan.entity.TransactionHeader;

import com.penjualan.repository.LoginRepository;
import com.penjualan.repository.TransactionDetailRepository;
import com.penjualan.repository.TransactionHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TransactionHeaderServiceImpl implements TransactionHeaderService{
    @Autowired
    private TransactionHeaderRepository transactionHeaderRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TransactionDetailRepository transactionDetailRepository;
    @Autowired
    private LoginRepository loginRepository;

    private final int rowsInPage = 10;

    @Override
    public void checkoutProduct(String user) {
        String documentCode = "TRX";
        List<KeranjangDTO> products = productService.getProductByUser(user);
        long total = 0;
        for(KeranjangDTO value: products){
            total = total + value.getSubtotal();
        }

        int number = 1;
        for(TransactionHeader value: transactionHeaderRepository.findAll()){
            if(value != null || value.getDocumentNumber() >= number){
                number = value.getDocumentNumber()+1;
            }
        }
        Login userLogin = loginService.getById(user);
        TransactionHeader transactionHeader = new TransactionHeader(documentCode, number, userLogin, total, LocalDate.now());

        transactionHeaderRepository.save(transactionHeader);

        for (KeranjangDTO value: products){
            int quantity = (int)value.getQuantity();
            Product product = productService.getById(value.getCode());
            TransactionDetail transactionDetail = new TransactionDetail(transactionHeader.getDocumentCode(), getById(number), product, value.getPrice(), quantity,
                    value.getUnit(), value.getSubtotal(), product.getCurrency());
            transactionHeader.addTransactionDetails(transactionDetail);
            transactionDetailRepository.save(transactionDetail);

        }
        userLogin.removeProduct();
        loginRepository.save(userLogin);

    }

    @Override
    public List<TransactionDTO> getReport(Integer page) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        Locale indonesia = new Locale("id", "ID");


        List<TransactionDTO> grid = transactionHeaderRepository.getReport(pagination);
        for(TransactionDTO value: grid){
            String totalRupiah = NumberFormat.getCurrencyInstance(indonesia).format(value.getTotal());
            value.setTotalRupiah(totalRupiah);
            value.setCodeNumber(value.getCode()+"-"+value.getNumber());
        }


        return grid;
    }

    @Override
    public long getTotalPages() {
        double totalData = (double)(transactionHeaderRepository.countReport());
        long totalPage = (long)(Math.ceil(totalData / rowsInPage));
        if(totalPage == 0){
            totalPage = 1;
        }
        return totalPage;    }

    public TransactionHeader getById(int id){
        Optional<TransactionHeader> optionalTransactionHeader = transactionHeaderRepository.findById(id);
        TransactionHeader transactionHeader = null;
        if(optionalTransactionHeader.isPresent()){
            transactionHeader = optionalTransactionHeader.get();
        }
        return transactionHeader;
    }
}
