package com.penjualan.service;

import com.penjualan.dto.TransactionDTO;

import java.util.List;

public interface TransactionHeaderService {

    void checkoutProduct(String user);

    List<TransactionDTO> getReport(Integer page);

    long getTotalPages();
}
