package com.musinsa.domain.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@Schema(description = "카테고리 별 최저가격 브랜드와 상품 정보")
public class CategoryLowestPriceDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "카테고리 별 최저가격 브랜드와 상품 응답 정보")
    public static class Response {
        @JsonProperty("카테고리")
        @Schema(description = "카테고리 정보")
        private List<CategoryInfo> categoryInfoList;
        @JsonProperty("총액")
        @Schema(description = "총액")
        private String totalLowestPrice;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "카테고리/브랜드/가격 정보")
    public static class CategoryInfo {
        @JsonProperty("카테고리")
        @Schema(description = "카테고리 이름")
        private String categoryName;
        @JsonProperty("브랜드")
        @Schema(description = "브랜드 이름")
        private String brandName;
        @JsonProperty("가격")
        @Schema(description = "가격")
        private String lowestPrice;
    }


    public interface Projection {
        String getCategoryName();

        BigInteger getLowestPrice();

        String getBrandName();

        BigInteger getTotalLowestPrice();
    }
}
