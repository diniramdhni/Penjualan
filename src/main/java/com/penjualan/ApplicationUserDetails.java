package com.penjualan;


import com.penjualan.entity.Login;
import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplicationUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public ApplicationUserDetails(Login login){
        this.username = login.getUser();
        this.password = login.getPassword();
        this.authorities.add(new SimpleGrantedAuthority("CUSTOMER"));

    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
//        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
