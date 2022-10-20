package com.penjualan.repository;


import com.penjualan.dto.TransactionDTO;
import com.penjualan.entity.TransactionHeader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionHeaderRepository extends JpaRepository<TransactionHeader, Integer> {
    @Query("""
			SELECT new com.penjualan.dto.TransactionDTO(th.documentCode, th.documentNumber, us.user, th.total, th.date, pro.name)
			FROM TransactionHeader AS th
			INNER JOIN th.user AS us
			INNER JOIN th.transactionDetails AS td
			INNER JOIN td.product AS pro
			""")
    public List<TransactionDTO> getReport(Pageable pagination);

	@Query("""
			SELECT COUNT(*)
			FROM TransactionHeader AS th
			INNER JOIN th.transactionDetails AS td
			INNER JOIN td.product AS pro
			""")
	public long countReport();
}
