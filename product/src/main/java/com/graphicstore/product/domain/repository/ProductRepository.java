package com.graphicstore.product.domain.repository;

import com.graphicstore.product.domain.agregate.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT name FROM product p WHERE p.id = :id")
    String findProductNameById(@Param("id") Integer id);

}
