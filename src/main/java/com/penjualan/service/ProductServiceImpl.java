package com.penjualan.service;


import com.penjualan.dto.KeranjangDTO;
import com.penjualan.dto.ProductGridDTO;
import com.penjualan.entity.Product;
import com.penjualan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    private final int rowsInPage = 10;

    @Override
    public List<ProductGridDTO> getProduct(Integer page, String name) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        List<ProductGridDTO> grid = productRepository.getProduct(name, pagination);

        for(ProductGridDTO value: grid){
            Locale indonesia = new Locale("id", "ID");

            String priceRupiah = NumberFormat.getCurrencyInstance(indonesia).format(value.getPrice());

            value.setPriceRupiah(priceRupiah);
            if(value.getDiscount() != 0){
                long discount  = value.getDiscount()*value.getPrice()/100;
                value.setPriceAfterDiscount(value.getPrice()-discount);
                String priceAfterDiscountRupiah = NumberFormat.getCurrencyInstance(indonesia).format(value.getPriceAfterDiscount());

                value.setPriceAfterDiscountRupiah(priceAfterDiscountRupiah);
            } else{
                value.setPriceAfterDiscountRupiah(priceRupiah);
            }


        }
        return grid;
    }

    @Override
    public ProductGridDTO getProduct(String name) {
        ProductGridDTO grid = productRepository.getProduct(name);
        System.out.println("room: " + grid);

            Locale indonesia = new Locale("id", "ID");

            String priceRupiah = NumberFormat.getCurrencyInstance(indonesia).format(grid.getPrice());

            grid.setPriceRupiah(priceRupiah);
            if(grid.getDiscount() != 0){
                long discount  = grid.getDiscount()*grid.getPrice()/100;
                grid.setPriceAfterDiscount(grid.getPrice()-discount);
                String priceAfterDiscountRupiah = NumberFormat.getCurrencyInstance(indonesia).format(grid.getPriceAfterDiscount());

                grid.setPriceAfterDiscountRupiah(priceAfterDiscountRupiah);
            } else{
                grid.setPriceAfterDiscountRupiah(priceRupiah);
            }


        return grid;
    }

    @Override
    public long getTotalPages(String name) {
        double totalData = (double)(productRepository.count(name));
        long totalPage = (long)(Math.ceil(totalData / rowsInPage));
        if(totalPage == 0){
            totalPage = 1;
        }
        return totalPage;
    }

    @Override
    public Product getById(String code) {
        Optional<Product> optionalProduct = productRepository.findById(code);
        Product product = null;
        if(optionalProduct.isPresent()){
            product = optionalProduct.get();
        }
        return product;
    }

    @Override
    public List<KeranjangDTO> getProductByUser(Integer page, String user) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        List<KeranjangDTO> grid = productRepository.getProductByUser(user, pagination);


        for(KeranjangDTO value: grid){
            long price = value.getPrice();
            System.out.println("price: " + price);
            Locale indonesia = new Locale("id", "ID");
            if(value.getDiscount() != null){
                 long discount  = value.getDiscount()*value.getPrice()/100;
                 price = price-discount;
                System.out.println("price after discount: " + price);
            }
            value.setSubtotal(price*value.getQuantity());
            System.out.println("sub total: " + value.getSubtotal());

            String subTotalRupiah = NumberFormat.getCurrencyInstance(indonesia).format(value.getSubtotal());

            value.setSubtotalRupiah(subTotalRupiah);
        }
        return grid;
    }

    @Override
    public List<KeranjangDTO> getProductByUser(String user) {
        List<KeranjangDTO> grid = productRepository.getProductByUser(user);


        for(KeranjangDTO value: grid){
            long price = value.getPrice();
            System.out.println("price: " + price);
            Locale indonesia = new Locale("id", "ID");
            if(value.getDiscount() != null){
                long discount  = value.getDiscount()*value.getPrice()/100;
                price = price-discount;
                System.out.println("price after discount: " + price);
            }
            value.setSubtotal(price*value.getQuantity());
            System.out.println("sub total: " + value.getSubtotal());

            String subTotalRupiah = NumberFormat.getCurrencyInstance(indonesia).format(value.getSubtotal());

            value.setSubtotalRupiah(subTotalRupiah);



        }
        return grid;
    }

    @Override
    public long getTotalPagesByUser(String name) {
        double totalData = (double)(productRepository.countByUser(name));
        long totalPage = (long)(Math.ceil(totalData / rowsInPage));
        if(totalPage == 0){
            totalPage = 1;
        }
        return totalPage;
    }


}
