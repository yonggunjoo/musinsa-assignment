package com.musinsa.domain.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@Schema(description = "단일 브랜드 카테고리별 상품, 최저가격 및 총액 정보")
public class BrandLowestPriceDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "응답 정보")
    public static class Response {
        @JsonProperty("최저가")
        @Schema(description = "최저가 정보")
        private Body body;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "최저가 정보")
    public static class Body {
        @JsonProperty("브랜드")
        @Schema(description = "브랜드 이름")
        private String brandName;
        @JsonProperty("카테고리")
        @Schema(description = "카테고리 목록")
        private List<CategoryInfo> categoryInfos;
        @JsonProperty("총액")
        @Schema(description = "총액")
        private String totalPrice;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "카테고리 정보")
    @Builder
    public static class CategoryInfo {
        @JsonProperty("카테고리")
        @Schema(description = "카테고리 이름")
        private String categoryName;
        @JsonProperty("가격")
        @Schema(description = "가격")
        private String price;
    }

    public interface Projection {
        String getBrandName();

        String getCategoryName();

        BigInteger getCategoryPrice();

        BigInteger getTotalPrice();
    }
}
