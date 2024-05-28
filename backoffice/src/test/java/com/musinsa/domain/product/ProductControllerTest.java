package com.musinsa.domain.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.domain.brand.dto.BrandDTO;
import com.musinsa.domain.category.dto.CategoryDTO;
import com.musinsa.domain.product.controller.ProductController;
import com.musinsa.domain.product.dto.ProductDTO;
import com.musinsa.domain.product.dto.request.CreateProductRequest;
import com.musinsa.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.domain.product.dto.request.PatchProductRequest;
import com.musinsa.domain.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

;

@WebMvcTest(ProductController.class)// 해당 클래스만 로드
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(MockitoAnnotations.openMocks(this));
    }

    @DisplayName("상품 추가")
    @Test
    void testCreateProduct() throws Exception {
        // Given
        // RequestBody
        CreateProductRequest request = CreateProductRequest.builder()
                .brandName("J")
                .categoryName("상의")
                .price(BigInteger.valueOf(13000))
                .build();
        // response
        BrandDTO brandDTO = BrandDTO.builder().id(10L).name("J").build();
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("상의").build();
        ProductDTO productDTO = ProductDTO.builder()
                .id(74L)
                .brand(brandDTO)
                .category(categoryDTO)
                .price(BigInteger.valueOf(13000))
                .build();
        ProductDTO.Response expectedResponse = ProductDTO.Response.builder()
                .product(productDTO).build();

        // when
        when(productService.createProduct(request)).thenReturn(expectedResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        // Then
        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.id", is(74)))
                .andExpect(jsonPath("$.product.brand.id", is(10)))
                .andExpect(jsonPath("$.product.brand.name", is(expectedResponse.getProduct().getBrand().getName())))
                .andExpect(jsonPath("$.product.category.id", is(1)))
                .andExpect(jsonPath("$.product.category.name", is(expectedResponse.getProduct().getCategory().getName())))
                .andExpect(jsonPath("$.product.price", is(13000)));
    }
    
    @DisplayName("상품 수정")
    @Test
    void testPatchProduct() throws Exception {
        // Given
        // RequestBody
        PatchProductRequest request = PatchProductRequest.builder()
                .id(74L)
                .brandName("Z")
                .categoryName("상의")
                .price(BigInteger.valueOf(12000))
                .build();
        // response
        BrandDTO brandDTO = BrandDTO.builder().id(10L).name("Z").build();
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("상의").build();
        ProductDTO productDTO = ProductDTO.builder()
                .id(74L)
                .brand(brandDTO)
                .category(categoryDTO)
                .price(BigInteger.valueOf(12000))
                .build();
        ProductDTO.Response expectedResponse = ProductDTO.Response.builder()
                .product(productDTO).build();

        // when
        when(productService.patchProduct(request)).thenReturn(expectedResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        // Then
        mockMvc.perform(patch("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.id", is(74)))
                .andExpect(jsonPath("$.product.brand.id", is(10)))
                .andExpect(jsonPath("$.product.brand.name", is(expectedResponse.getProduct().getBrand().getName())))
                .andExpect(jsonPath("$.product.category.id", is(1)))
                .andExpect(jsonPath("$.product.category.name", is(expectedResponse.getProduct().getCategory().getName())))
                .andExpect(jsonPath("$.product.price", is(12000)));
    }

    @DisplayName("상품 삭제")
    @Test
    void testDeleteProduct() throws Exception {
        // Given
        // RequestBody
        DeleteProductRequest request = DeleteProductRequest.builder()
                .id(74L)
                .build();
        // response
        ProductDTO productDTO = ProductDTO.builder()
                .id(74L)
                .build();
        ProductDTO.Response expectedResponse = ProductDTO.Response.builder()
                .product(productDTO).build();

        // when
        when(productService.deleteProduct(request)).thenReturn(expectedResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        // Then
        mockMvc.perform(delete("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.id", is(74)));
    }
}
