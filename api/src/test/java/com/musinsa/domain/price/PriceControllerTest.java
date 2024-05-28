package com.musinsa.domain.price;

import com.musinsa.domain.price.controller.PriceController;
import com.musinsa.domain.price.dto.BrandLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryLowestPriceDTO;
import com.musinsa.domain.price.dto.CategoryPriceSearchDTO;
import com.musinsa.domain.price.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)// 해당 클래스만 로드
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(MockitoAnnotations.openMocks(this));
    }

    @DisplayName("구현1 항목 테스트")
    @Test
    void testGetCategoryLowestPriceBrand() throws Exception {
        // Given
        List<CategoryLowestPriceDTO.CategoryInfo> categoryInfoList = new ArrayList<>();
        categoryInfoList.add(CategoryLowestPriceDTO.CategoryInfo.builder()
                .categoryName("상의")
                .brandName("C")
                .lowestPrice("10,000").build());
        String totalLowestPrice = "10,000";

        CategoryLowestPriceDTO.Response expectedResponse = CategoryLowestPriceDTO.Response.builder()
                .categoryInfoList(categoryInfoList)
                .totalLowestPrice(totalLowestPrice).build();

        // when
        when(priceService.getCategoryLowestPriceBrand()).thenReturn(expectedResponse);

        // Then
        mockMvc.perform(get("/price/category/lowest-price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.카테고리", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.카테고리[0].카테고리", is(expectedResponse.getCategoryInfoList().get(0).getCategoryName())))
                .andExpect(jsonPath("$.카테고리[0].브랜드", is(expectedResponse.getCategoryInfoList().get(0).getBrandName())))
                .andExpect(jsonPath("$.카테고리[0].가격", is(expectedResponse.getCategoryInfoList().get(0).getLowestPrice())))
                .andExpect(jsonPath("$.총액", is(expectedResponse.getTotalLowestPrice())));
    }

    @DisplayName("구현2 항목 테스트")
    @Test
    void testGetSingleBrandCategoryLowestPrice() throws Exception {
        // Given
        List<BrandLowestPriceDTO.CategoryInfo> categoryInfoList = new ArrayList<>();
        categoryInfoList.add(BrandLowestPriceDTO.CategoryInfo.builder()
                .categoryName("상의")
                .price("10,000").build());

        String totalPrice = "10,000";

        BrandLowestPriceDTO.Body body = BrandLowestPriceDTO.Body.builder()
                .brandName("A")
                .categoryInfos(categoryInfoList)
                .totalPrice(totalPrice)
                .build();

        BrandLowestPriceDTO.Response expectedResponse = BrandLowestPriceDTO.Response.builder()
                .body(body)
                .build();

        // when
        when(priceService.getSingleBrandCategoryLowestPrice()).thenReturn(expectedResponse);

        // Then
        mockMvc.perform(get("/price/brand/lowest-price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.최저가.브랜드", is(expectedResponse.getBody().getBrandName())))
                .andExpect(jsonPath("$.최저가.카테고리[0].카테고리", is(expectedResponse.getBody().getCategoryInfos().get(0).getCategoryName())))
                .andExpect(jsonPath("$.최저가.카테고리[0].가격", is(expectedResponse.getBody().getCategoryInfos().get(0).getPrice())))
                .andExpect(jsonPath("$.최저가.총액", is(expectedResponse.getBody().getTotalPrice())));
    }

    @DisplayName("구현3 항목 테스트")
    @Test
    void testGetCategoryPriceSearch() throws Exception {
        // Given
        // 브랜드 정보
        CategoryPriceSearchDTO.BrandInfo lowestPriceInfo = CategoryPriceSearchDTO.BrandInfo.builder()
                .brandName("C").price("10,000")
                .build();
        CategoryPriceSearchDTO.BrandInfo HighestPriceInfo = CategoryPriceSearchDTO.BrandInfo.builder()
                .brandName("I").price("11,400")
                .build();
        // 최저가
        List<CategoryPriceSearchDTO.BrandInfo> lowestPriceInfoList = new ArrayList<>();
        lowestPriceInfoList.add(lowestPriceInfo);
        // 최고가
        List<CategoryPriceSearchDTO.BrandInfo> HighestPriceInfoList = new ArrayList<>();
        HighestPriceInfoList.add(HighestPriceInfo);

        CategoryPriceSearchDTO.Response expectedResponse = CategoryPriceSearchDTO.Response.builder()
                .categoryName("상의")
                .lowestPriceInfo(lowestPriceInfoList)
                .HighestPriceInfo(HighestPriceInfoList).build();

        // when
        when(priceService.getCategoryPriceSearch("상의")).thenReturn(expectedResponse);

        // Then
        mockMvc.perform(get("/price/search")
                        .param("카테고리명", "상의"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.카테고리", is(expectedResponse.getCategoryName())))
                .andExpect(jsonPath("$.최저가", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.최저가[0].브랜드", is(expectedResponse.getLowestPriceInfo().get(0).getBrandName())))
                .andExpect(jsonPath("$.최저가[0].가격", is(expectedResponse.getLowestPriceInfo().get(0).getPrice())))
                .andExpect(jsonPath("$.최고가", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.최고가[0].브랜드", is(expectedResponse.getHighestPriceInfo().get(0).getBrandName())))
                .andExpect(jsonPath("$.최고가[0].가격", is(expectedResponse.getHighestPriceInfo().get(0).getPrice())));
    }
}
