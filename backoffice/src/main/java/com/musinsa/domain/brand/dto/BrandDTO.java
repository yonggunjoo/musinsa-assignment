package com.musinsa.domain.brand.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.entity.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "브랜드 정보")
public class BrandDTO {
    @Schema(description = "브랜드 아이디")
    private Long id;
    @Schema(description = "브랜드 이름")
    private String name;

    @Data
    @Builder
    @Schema(description = "브랜드 응답 정보")
    public static class Response {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "단일 브랜드 정보")
        private BrandDTO brand;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "브랜드 목록 정보")
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
