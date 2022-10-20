package com.penjualan.service;


import com.penjualan.ApplicationUserDetails;
import com.penjualan.configuration.MvcSecurityConfig;
import com.penjualan.dto.RegisterDTO;
import com.penjualan.entity.Login;
import com.penjualan.entity.Product;
import com.penjualan.repository.LoginRepository;
import com.penjualan.repository.ProductRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;
    private String user;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean checkExistingUsername(String username) {
        Long totalUser = loginRepository.count(username);
        return totalUser > 0 ? true : false;
    }

    @Override
    public void register(RegisterDTO dto) {
        PasswordEncoder passwordEncoder = MvcSecurityConfig.passwordEncoder();
        //encode password
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        //initialize account
        Login account = new Login(
                dto.getUsername(),
                hashPassword
        );

        //save akun
       loginRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        Optional<Login> optionalLogin = loginRepository.findById(user);
        Login login = null;
        if(optionalLogin.isPresent()){
            login = optionalLogin.get();
            this.user = login.getUser();

        }
        return new ApplicationUserDetails(login);
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public Login getById(String user){
        Optional<Login> optionalLogin = loginRepository.findById(user);
        Login login = null;
        if(optionalLogin.isPresent()){
            login = optionalLogin.get();
        }
        return login;
    }
    @Override
    public void insertProduct(String code) {
        Login user = getById(this.user);
        Product product = productService.getById(code);

        user.addProducts(product);
//        product.addUser(user);
        loginRepository.save(user);
//        productRepository.save(product);

    }

    @Override
    public void deleteProduct(String code) {
        Login user = getById(this.user);
        Product product = productService.getById(code);
        user.deleteProduct(product);
        productRepository.save(product);
    }


}
