package com.musinsa.domain.category.service;


import com.musinsa.domain.category.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.category.dto.CategoryPriceSearchDTO;

public interface CategoryService {
    CategoryLowestPriceDTO.Response getCategoryLowestPriceBrand();

    CategoryPriceSearchDTO.Response getCategoryPriceSearch(String categoryName);
}
