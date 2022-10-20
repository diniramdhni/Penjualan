package com.penjualan.repository;

import com.penjualan.entity.TransactionDetail;
import com.penjualan.entity.TransactionDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, TransactionDetailKey> {
}
