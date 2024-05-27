package com.musinsa.domain.brand.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
public class BrandLowestPriceDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        @JsonProperty("최저가")
        private Body body;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Body {
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("카테고리")
        private List<CategoryInfo> categoryInfos;
        @JsonProperty("총액")
        private String totalPrice;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryInfo {
        @JsonProperty("카테고리")
        private String categoryName;
        @JsonProperty("가격")
        private String price;
    }

    public interface Projection {
        String getBrandName();

        String getCategoryName();

        BigInteger getCategoryPrice();

        BigInteger getTotalPrice();
    }
}
