package com.penjualan.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {




    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/product/index");
        registry.addViewController("/product").setViewName("forward:/product/index");
        registry.addViewController("/checkout").setViewName("forward:/checkout/index");
        registry.addViewController("/transaction").setViewName("forward:/transaction/index");
    }
}
