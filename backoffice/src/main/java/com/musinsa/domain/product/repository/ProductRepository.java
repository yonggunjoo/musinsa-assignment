package com.musinsa.domain.product.repository;

import com.musinsa.entity.Brand;
import com.musinsa.entity.Category;
import com.musinsa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @Query("SELECT p FROM Product p JOIN FETCH p.brand JOIN FETCH p.category")
    List<Product> findAll();

    boolean existsByBrandAndCategory(Brand brand, Category category);
}
