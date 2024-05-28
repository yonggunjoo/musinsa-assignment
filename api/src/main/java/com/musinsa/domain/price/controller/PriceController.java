package com.musinsa.domain.price.controller;

import com.musinsa.domain.price.dto.BrandLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryPriceSearchDTO;
import com.musinsa.domain.price.exception.PriceErrorCode;
import com.musinsa.domain.price.service.PriceService;
import com.musinsa.global.common.exception.RestApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
@Tag(name = "가격 정보 조회 컨트롤러", description = "가격 정보 조회 컨트롤러")
public class PriceController {
    private final PriceService priceService;

    /**
     * 구현 1) - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
     * ● 요청값 : 없음
     * ● 응답값 : Frontend에서 값 변경없이 아래와 같은 화면을 출력하기 위한 JSON
     */
    @GetMapping("/category/lowest-price")
    @Operation(summary = "구현1 항목", description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API")
    public CategoryLowestPriceDTO.Response getCategoryLowestPriceBrand() {
        return priceService.getCategoryLowestPriceBrand();
    }

    /**
     * 구현 2) - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
     * 조회하는 API
     */
    @GetMapping("/brand/lowest-price")
    @Operation(summary = "구현 2 항목", description = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API")
    public BrandLowestPriceDTO.Response getSingleBrandCategoryLowestPrice() {
        return priceService.getSingleBrandCategoryLowestPrice();
    }

    /**
     * 구현 3) - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
     * ● 요청값 : 카테고리명 (String)
     * ● 응답값
     */
    @GetMapping("/search")
    @Operation(summary = "구현3 항목", description = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API")
    public CategoryPriceSearchDTO.Response getCategoryPriceSearch(@RequestParam("카테고리명") @NotBlank(message = "카테고리명은 필수 정보 입니다.") String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new RestApiException(PriceErrorCode.BAD_REQUEST);
        }
        return priceService.getCategoryPriceSearch(categoryName);
    }
}
