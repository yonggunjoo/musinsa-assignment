package com.musinsa.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@NoArgsConstructor
public class CategoryPriceSearchDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        @JsonProperty("카테고리")
        private String categoryName;
        @JsonProperty("최저가")
        private List<BrandInfo> lowestPriceInfo;
        @JsonProperty("최고가")
        private List<BrandInfo> HighestPriceInfo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BrandInfo {
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("가격")
        private String price;
    }

    public interface Projection {

        String getCategoryName();

        String getBrandName();

        BigInteger getHighestPrice();

        BigInteger getLowestPrice();
    }
}
