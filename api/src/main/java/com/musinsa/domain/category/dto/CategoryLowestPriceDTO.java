package com.musinsa.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
public class CategoryLowestPriceDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private List<CategoryInfo> categoryInfoList;
        @JsonProperty("총액")
        private String totalLowestPrice;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryInfo {
        @JsonProperty("카테고리")
        private String categoryName;
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("가격")
        private String lowestPrice;
    }


    public interface Projection {
        String getCategoryName();

        BigInteger getLowestPrice();

        String getBrandName();

        BigInteger getTotalLowestPrice();
    }
}
