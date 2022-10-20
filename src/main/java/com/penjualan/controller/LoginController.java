package com.penjualan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-login")
public class LoginController {

    @GetMapping ("/showMyLoginPage")
    public String showLoginPage(Model model){

        return "login/login-form";
    }
}
