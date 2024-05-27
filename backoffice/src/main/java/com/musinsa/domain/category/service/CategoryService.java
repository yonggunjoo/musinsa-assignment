package com.musinsa.domain.category.service;


import com.musinsa.common.entity.Category;

public interface CategoryService {
    boolean existsByName(String name);


    Category findByName(String name);
}
