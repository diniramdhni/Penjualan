package com.penjualan.controller;

import com.penjualan.dto.RegisterDTO;
import com.penjualan.service.LoginService;
import com.penjualan.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private LoginService loginService;




    @GetMapping("/registerForm")
    public String registerForm(Model model){
        RegisterDTO dto = new RegisterDTO();

        model.addAttribute("account", dto);

        return "login/register-form";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("account") RegisterDTO dto,
            BindingResult bindingResult,
            Model model){
        if(bindingResult.hasErrors()){
            return "login/register-form";
        }
        loginService.register(dto);
        return "redirect:/my-login/showMyLoginPage";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        return "access-denied";
    }




}
