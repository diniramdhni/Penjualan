package com.penjualan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @Id
    @Column(name = "[User]")
    private String user;
    @Column(name = "Password")
    private String password;


    @ManyToMany
    @JoinTable(
            name = "Keranjang",
            joinColumns = @JoinColumn(name = "[User]"),
            //karna ada di tabel student, makanya join kolom yg pertama student id
            inverseJoinColumns = @JoinColumn(name = "Product_Code")
    )
    private List<Product> products;

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public void addProducts (Product products) {
        if(this.products == null){
            this.products = new LinkedList<>();
        }
        this.products.add(products);
    }

    public void deleteProduct(Product product){

           this.products.remove(product);

    }

    public void removeProduct() {
        this.products.clear();
    }
}
