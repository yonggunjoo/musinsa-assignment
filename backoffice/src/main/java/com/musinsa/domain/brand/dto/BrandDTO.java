package com.musinsa.domain.brand.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.entity.Brand;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrandDTO {
    private Long id;
    private String name;

    @Data
    @Builder
    public static class Response {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private BrandDTO brand;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<BrandDTO> brands;
    }

    public static BrandDTO convertToBrandDTO(Brand entity) {
        return BrandDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static Brand convertToBrand(BrandDTO dto) {
        return Brand.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
