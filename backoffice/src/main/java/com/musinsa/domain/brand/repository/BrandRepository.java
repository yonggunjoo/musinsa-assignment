package com.musinsa.domain.brand.repository;

import com.musinsa.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);

    Brand findByName(String name);
}
