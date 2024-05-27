package com.musinsa.domain.product.repository;

import com.musinsa.entity.Brand;
import com.musinsa.entity.Category;
import com.musinsa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByBrandAndCategory(Brand brand, Category category);
}
