package com.musinsa.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.entity.Product;
import com.musinsa.domain.brand.dto.BrandDTO;
import com.musinsa.domain.category.dto.CategoryDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private BrandDTO brand;
    private CategoryDTO category;
    private BigInteger price;

    @Data
    @Builder
    public static class Response {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private ProductDTO product;

        @JsonInclude(JsonInclude.Include.NON_NULL)
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
