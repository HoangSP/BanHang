package com.thienhoang.banhang.dao;

import com.thienhoang.banhang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductDao extends JpaRepository<Product, Integer> {
    @Query("SELECT u FROM Product u WHERE u.name LIKE :param")
    List<Product> search(@Param("param") String name);

    @Query("select count (p) as sanphan from Product p")
    int countByName();
}
