package com.penjualan.repository;


import com.penjualan.dto.KeranjangDTO;
import com.penjualan.dto.ProductGridDTO;
import com.penjualan.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("""
			SELECT new com.penjualan.dto.ProductGridDTO(pro.code, pro.name, pro.discount, pro.price)
			FROM Product AS pro
			where pro.name LIKE %:name%
			""")
    public List<ProductGridDTO> getProduct(@Param("name") String name, Pageable pagination);


	@Query("""
			SELECT COUNT(*)
			FROM Product AS pro
			where pro.name LIKE %:name%
			""")
	public long count(@Param("name") String name);

	@Query("""
			SELECT new com.penjualan.dto.ProductGridDTO(pro.code, pro.name, pro.discount, pro.price, pro.dimension, pro.unit)
			FROM Product AS pro
			where pro.code = :code
			""")
	public ProductGridDTO getProduct(@Param("code") String code);

	@Query("""
			SELECT new com.penjualan.dto.KeranjangDTO(pro.code, us.user, pro.name, count(pro.code), pro.price, pro.discount, pro.unit)
			FROM Product AS pro
			INNER JOIN pro.user AS us
			where us.user = :user
			GROUP BY pro.code, us.user, pro.name, pro.price, pro.discount, pro.unit
			""")
	public List<KeranjangDTO> getProductByUser(@Param("user") String user, Pageable pagination);

	@Query("""
			SELECT COUNT(*)
			FROM Product AS pro
			INNER JOIN pro.user AS us 
			where us.user = :user
			""")
	public long countByUser(@Param("user") String user);

	@Query("""
			SELECT new com.penjualan.dto.KeranjangDTO(pro.code, us.user, pro.name, count(pro.code), pro.price, pro.discount, pro.unit)
			FROM Product AS pro
			INNER JOIN pro.user AS us
			where us.user = :user
			GROUP BY pro.code, us.user, pro.name, pro.price, pro.discount, pro.unit
			""")
	public List<KeranjangDTO> getProductByUser(@Param("user") String user);

}

