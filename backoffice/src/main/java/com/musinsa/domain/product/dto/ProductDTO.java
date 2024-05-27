package com.musinsa.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.entity.Product;
import com.musinsa.domain.brand.dto.BrandDTO;
import com.musinsa.domain.category.dto.CategoryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@Schema(description = "상품 정보")
public class ProductDTO {
    @Schema(description = "상품 아이디")
    private Long id;
    @Schema(description = "브랜드 정보")
    private BrandDTO brand;
    @Schema(description = "카테고리 정보")
    private CategoryDTO category;
    @Schema(description = "가격 정보")
    private BigInteger price;

    @Data
    @Builder
    @Schema(description = "상품 응답 정보")
    public static class Response {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "상품 단일 정보")
        private ProductDTO product;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "상품 목록 정보")
        private List<ProductDTO> products;
    }

    public static ProductDTO convertToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .brand(Objects.nonNull(product.getBrand()) ? BrandDTO.convertToBrandDTO(product.getBrand()) : null)
                .category(Objects.nonNull(product.getCategory()) ? CategoryDTO.convertToCategoryDTO(product.getCategory()) : null)
                .price(product.getPrice())
                .build();
    }

    public static Product convertToProduct(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .brand(BrandDTO.convertToBrand(dto.getBrand()))
                .category(CategoryDTO.convertToCategory(dto.getCategory()))
                .price(dto.getPrice())
                .build();
    }


}
