package com.penjualan.controller;

import com.penjualan.dto.KeranjangDTO;
import com.penjualan.dto.ProductGridDTO;
import com.penjualan.service.LoginService;
import com.penjualan.service.ProductService;
import com.penjualan.service.TransactionHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private ProductService productService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private TransactionHeaderService transactionHeaderService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        Model model){
        List<KeranjangDTO> grid = productService.getProductByUser(page, loginService.getUser());
        long total = 0;
        for (KeranjangDTO value: grid){

            total = total + value.getSubtotal();
        }
        Locale indonesia = new Locale("id", "ID");
        String totalRupiah = NumberFormat.getCurrencyInstance(indonesia).format(total);

        long totalPages = productService.getTotalPagesByUser(loginService.getUser());
        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("total", totalRupiah);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("user", loginService.getUser());
        model.addAttribute("breadCrumbs", "Welcome  " + loginService.getUser());
        return "checkout/checkout-index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String code){
        System.out.println("di dalam delete checkout");
        loginService.deleteProduct(code);
        return "redirect:/checkout/index";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam(required = true) String user){

        transactionHeaderService.checkoutProduct(user);
        return "redirect:/checkout/index";
    }
}
