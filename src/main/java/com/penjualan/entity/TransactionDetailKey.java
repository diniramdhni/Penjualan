package com.penjualan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailKey implements Serializable {

    @Column(name = "Document_Number")
    private Integer documentNumber;
    @Column(name = "Product_Code")
    private String productCode;
}
