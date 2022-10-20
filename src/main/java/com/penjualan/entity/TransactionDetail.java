package com.penjualan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transaction_Detail")
public class TransactionDetail {
    @EmbeddedId
    private TransactionDetailKey id;

    @Column(name = "Document_Code")
    private String documentCode;

    @ManyToOne
    @MapsId("documentNumber")
    @JoinColumn(name = "Document_Number")
    private TransactionHeader transactionHeader;



    @ManyToOne
    @MapsId("productCode")
    @JoinColumn(name = "Product_Code")
    private Product product;

    @Column(name = "Price")
    private long price;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "Unit")
    private String unit;
    @Column(name = "Sub_Total")
    private long subTotal;
    @Column(name = "Currency")
    private String currency;

    public TransactionDetail(String documentCode, TransactionHeader transactionHeader, Product product, long price, int quantity, String unit, long subTotal, String currency) {
        this.id = new TransactionDetailKey(transactionHeader.getDocumentNumber(), product.getCode());
        this.documentCode = documentCode;
        this.transactionHeader = transactionHeader;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.subTotal = subTotal;
        this.currency = currency;
    }
}
