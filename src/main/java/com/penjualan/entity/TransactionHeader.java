package com.penjualan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Transaction_Header")
public class TransactionHeader {
    @Column(name = "Document_Code", length = 3)
    private String documentCode;
    @Id
    @Column(name = "Document_Number", length = 10)
    private Integer documentNumber;
    @ManyToOne
    @JoinColumn(name = "[User]")
    private Login user;
    @Column(name = "Total", length = 10)
    private long total;
    @Column(name = "Date", length = 10)
    private LocalDate date;
    @OneToMany(mappedBy = "transactionHeader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionDetail> transactionDetails;


    public TransactionHeader(String documentCode, Integer documentNumber, Login user, long total, LocalDate date) {
        this.documentCode = documentCode;
        this.documentNumber = documentNumber;
        this.user = user;
        this.total = total;
        this.date = date;
    }

    public void addTransactionDetails(TransactionDetail transactionDetails) {
        if(this.transactionDetails == null){
            this.transactionDetails = new LinkedList<>();
        }
        this.transactionDetails.add(transactionDetails);
    }
}
