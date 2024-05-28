package com.musinsa.domain.price.service;


import com.musinsa.domain.price.dto.BrandLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryPriceSearchDTO;

public interface PriceService {
    CategoryLowestPriceDTO.Response getCategoryLowestPriceBrand();

    BrandLowestPriceDTO.Response getSingleBrandCategoryLowestPrice();

    CategoryPriceSearchDTO.Response getCategoryPriceSearch(String categoryName);
}
