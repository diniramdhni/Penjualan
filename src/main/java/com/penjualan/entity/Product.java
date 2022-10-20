package com.penjualan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "Product_Code")
    private String code;
    @Column(name = "Product_Name")
    private String name;
    @Column(name = "Price")
    private long price;
    @Column(name = "Currency")
    private String currency;
    @Column(name = "Discount")
    private long discount = 0;
    @Column(name = "Dimension")
    private String dimension;
    @Column(name = "Unit")
    private String unit;

    @ManyToMany
    @JoinTable(
            name = "Keranjang",
            joinColumns = @JoinColumn(name = "Product_Code"),

            inverseJoinColumns = @JoinColumn(name = "[User]")
    )
    private List<Login> user;

    @OneToMany(mappedBy = "product")
    private List<TransactionDetail> transactionDetails;

    public void addUser(Login user) {
        if(this.user == null){
            this.user = new LinkedList<>();
        }
        this.user.add(user);
    }
}
