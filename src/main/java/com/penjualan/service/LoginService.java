package com.penjualan.service;

import com.penjualan.dto.RegisterDTO;
import com.penjualan.entity.Login;

public interface LoginService {
    boolean checkExistingUsername(String username);

    void register(RegisterDTO dto);
    public String getUser();

    void insertProduct(String code);

    void deleteProduct(String code);
    public Login getById(String user);


}
