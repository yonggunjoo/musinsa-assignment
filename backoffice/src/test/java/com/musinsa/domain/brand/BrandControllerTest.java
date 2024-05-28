package com.musinsa.domain.brand;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.domain.brand.controller.BrandController;
import com.musinsa.domain.brand.dto.BrandDTO;
import com.musinsa.domain.brand.dto.request.CreateBrandRequest;
import com.musinsa.domain.brand.dto.request.DeleteBrandRequest;
import com.musinsa.domain.brand.dto.request.PatchBrandRequest;
import com.musinsa.domain.brand.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

;

@WebMvcTest(BrandController.class)// 해당 클래스만 로드
public class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(MockitoAnnotations.openMocks(this));
    }

    @DisplayName("브랜드 추가 'J'")
    @Test
    void testCreateBrand() throws Exception {
        // Given
        // RequestBody
        CreateBrandRequest request = CreateBrandRequest.builder().brandName("J").build();
        // response
        BrandDTO brand = BrandDTO.builder().id(10L).name("J").build();
        BrandDTO.Response expectedResponse = BrandDTO.Response.builder()
                .brand(brand).build();

        // when
        when(brandService.createBrand(request)).thenReturn(expectedResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        // Then
        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand.id", is(10)))
                .andExpect(jsonPath("$.brand.name", is(expectedResponse.getBrand().getName())));
    }
    
    @DisplayName("브랜드 수정")
    @Test
    void testPatchBrand() throws Exception {
        // Given
        // RequestBody
        // 'J' -> 'Z'
        PatchBrandRequest request = PatchBrandRequest.builder().id(10L).brandName("Z").build();
        // response
        BrandDTO brand = BrandDTO.builder().id(10L).name("Z").build();
        BrandDTO.Response expectedResponse = BrandDTO.Response.builder()
                .brand(brand).build();

        // when
        when(brandService.patchBrand(request)).thenReturn(expectedResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        // Then
        mockMvc.perform(patch("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand.id", is(10)))
                .andExpect(jsonPath("$.brand.name", is(expectedResponse.getBrand().getName())));
    }

    @DisplayName("브랜드 삭제")
    @Test
    void testDeleteBrand() throws Exception {
        // Given
        // RequestBody
        DeleteBrandRequest request = DeleteBrandRequest.builder().id(10L).build();
        // response
        BrandDTO brand = BrandDTO.builder().id(10L).name("Z").build();
        BrandDTO.Response expectedResponse = BrandDTO.Response.builder()
                .brand(brand).build();

        // when
        when(brandService.deleteBrand(request)).thenReturn(expectedResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        // Then
        mockMvc.perform(delete("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand.id", is(10)))
                .andExpect(jsonPath("$.brand.name", is(expectedResponse.getBrand().getName())));
    }
}
