package com.musinsa.domain.category.service;


import com.musinsa.entity.Category;

public interface CategoryService {
    boolean existsByName(String name);


    Category findByName(String name);
}
