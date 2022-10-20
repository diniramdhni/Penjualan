package com.penjualan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductGridDTO {
    private String code;
    private String name;
    private Long discount;
    private long price;
    private long priceAfterDiscount;
    private String priceRupiah;
    private String priceAfterDiscountRupiah;
    private String dimension;
    private String unit;

    public ProductGridDTO(String code, String name, long discount, long price) {
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.price = price;
    }

    public ProductGridDTO(String code, String name, Long discount, long price, String dimension, String unit) {
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.price = price;
        this.dimension = dimension;
        this.unit = unit;
    }
}
