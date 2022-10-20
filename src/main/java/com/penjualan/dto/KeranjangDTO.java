package com.penjualan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeranjangDTO {
    private String code;
    private String user;
    private String name;
    private long quantity;
    private long price;
    private long subtotal;
    private String subtotalRupiah;
    private String unit;

    private Long discount;

    public KeranjangDTO(String code, String user, String name, long quantity, long price, long discount, String unit) {
        this.code = code;
        this.user = user;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.unit = unit;
    }
}
