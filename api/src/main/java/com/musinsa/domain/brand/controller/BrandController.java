package com.musinsa.domain.brand.controller;

import com.musinsa.domain.brand.dto.BrandLowestPriceDTO;
import com.musinsa.domain.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    /**
     * 구현 2) - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
     * 조회하는 API
     */
    @GetMapping("/lowest-price/info")
    public BrandLowestPriceDTO.Response getSingleBrandCategoryLowestPrice() {
        return brandService.getSingleBrandCategoryLowestPrice();
    }
}
