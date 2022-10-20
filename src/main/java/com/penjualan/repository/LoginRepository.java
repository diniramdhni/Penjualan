package com.penjualan.repository;


import com.penjualan.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, String> {
    @Query("""
            SELECT COUNT(*)
            FROM Login AS log
            WHERE log.user = :username
            """)
    Long count(@Param("username") String username);
}
