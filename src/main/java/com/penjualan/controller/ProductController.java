package com.penjualan.controller;

import com.penjualan.dto.ProductGridDTO;
import com.penjualan.service.LoginService;
import com.penjualan.service.ProductService;
import com.penjualan.service.TransactionHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private LoginService loginService;

    @Autowired
    private TransactionHeaderService transactionHeaderService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue="") String name,
                        Model model){
        List<ProductGridDTO> grid = productService.getProduct(page, name);
        long totalPages = productService.getTotalPages(name);
        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Welcome  " + loginService.getUser());
        return "product/product-index";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) String code,
                         Model model) {

        ProductGridDTO dto = productService.getProduct(code);

        model.addAttribute("grid", dto);
        return "product/product-detail";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam(required = true) String code) {

        loginService.insertProduct(code);


        return "redirect:/checkout/index";
    }
}
