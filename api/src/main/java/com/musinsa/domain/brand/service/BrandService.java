package com.musinsa.domain.brand.service;


import com.musinsa.domain.brand.dto.BrandLowestPriceDTO;

public interface BrandService {
    BrandLowestPriceDTO.Response getSingleBrandCategoryLowestPrice();
}
