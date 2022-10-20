package com.penjualan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private String code;
    private int number;
    private String codeNumber;
    private String user;
    private long total;
    private String totalRupiah;
    private LocalDate date;
    private String product;

    public TransactionDTO(String code, int number, String user, long total, LocalDate date, String product) {
        this.code = code;
        this.number = number;
        this.user = user;
        this.total = total;
        this.date = date;
        this.product = product;
    }
}
