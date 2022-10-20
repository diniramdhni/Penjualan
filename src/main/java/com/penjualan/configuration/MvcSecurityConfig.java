package com.penjualan.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

//    mendefinisikan BCryptPasswordEncoder
//     algoritma BCrypt menghasilkan String dengan panjang 60
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(7);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/css/**", "/resources/fonts/**", "/resources/image/**",
                        "/resources/js/**", "/resources/webfonts/**", "/account/**").permitAll() //biar bisa baca file di direktori css
                .anyRequest().authenticated() //request autentikasi
                .and()
                .formLogin()
                .loginPage("/my-login/showMyLoginPage") //buat custom login
                .loginProcessingUrl("/authenticateTheUser") //kalo dah login bakal ngarah ke link /
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/home/accessDenied"); //kalo akses ga bisa ngarah ke home/acces denied itu
    }

    //inisialisasi akun dri database
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
