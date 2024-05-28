package com.musinsa.domain.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@NoArgsConstructor
@Schema(description = "카테고리 별 최저/최고가 정보")
public class CategoryPriceSearchDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "카테고리 별 최저/최고가 응답 정보")
    public static class Response {
        @JsonProperty("카테고리")
        @Schema(description = "카테고리 이름")
        private String categoryName;
        @JsonProperty("최저가")
        @Schema(description = "최저가")
        private List<BrandInfo> lowestPriceInfo;
        @JsonProperty("최고가")
        @Schema(description = "최고가")
        private List<BrandInfo> HighestPriceInfo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "브랜드 가격 정보")
    public static class BrandInfo {
        @JsonProperty("브랜드")
        @Schema(description = "브랜드 이름")
        private String brandName;
        @JsonProperty("가격")
        @Schema(description = "가격")
        private String price;
    }

    public interface Projection {

        String getCategoryName();

        String getBrandName();

        BigInteger getHighestPrice();

        BigInteger getLowestPrice();
    }
}
