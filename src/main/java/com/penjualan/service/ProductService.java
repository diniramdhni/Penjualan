package com.penjualan.service;

import com.penjualan.dto.KeranjangDTO;
import com.penjualan.dto.ProductGridDTO;
import com.penjualan.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductGridDTO> getProduct(Integer page, String name);
    

    ProductGridDTO getProduct(String name);

    long getTotalPages(String name);

    Product getById(String code);

    List<KeranjangDTO> getProductByUser(Integer page, String user);

    List<KeranjangDTO> getProductByUser( String user);
    long getTotalPagesByUser(String name);
}
