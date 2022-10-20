package com.penjualan.controller;

import com.penjualan.dto.ProductGridDTO;
import com.penjualan.dto.TransactionDTO;
import com.penjualan.service.LoginService;
import com.penjualan.service.TransactionHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionHeaderService transactionHeaderService;
    @Autowired
    private LoginService loginService;
    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        Model model){
        List<TransactionDTO> grid = transactionHeaderService.getReport(page);

        long totalPages = transactionHeaderService.getTotalPages();
        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Welcome  " + loginService.getUser());
        return "transaction/transaction-index";
    }
}
