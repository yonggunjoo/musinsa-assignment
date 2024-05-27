package com.musinsa.domain.category.controller;

import com.musinsa.domain.category.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.category.dto.CategoryPriceSearchDTO;
import com.musinsa.domain.category.exception.CategoryErrorCode;
import com.musinsa.domain.category.service.CategoryService;
import com.musinsa.global.common.exception.RestApiException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 구현 1) - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
     * ● 요청값 : 없음
     * ● 응답값 : Frontend에서 값 변경없이 아래와 같은 화면을 출력하기 위한 JSON
     */
    @GetMapping("/lowest-price/info")
    public CategoryLowestPriceDTO.Response getCategoryLowestPriceBrand() {
        return categoryService.getCategoryLowestPriceBrand();
    }

    /**
     * 구현 3) - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
     * ● 요청값 : 카테고리명 (String)
     * ● 응답값
     */
    @GetMapping("/price/search")
    public CategoryPriceSearchDTO.Response getCategoryPriceSearch(@RequestParam("카테고리명") @NotBlank(message = "카테고리명은 필수 정보 입니다.") String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new RestApiException(CategoryErrorCode.BAD_REQUEST);
        }
        return categoryService.getCategoryPriceSearch(categoryName);
    }
}
