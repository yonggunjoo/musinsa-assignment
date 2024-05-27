package com.musinsa.domain.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotNull(message = "브랜드 이름은 필수 정보 입니다.")
    private String brandName;
    @NotNull(message = "카테고리 이름은 필수 정보 입니다.")
    private String categoryName;
    @NotNull(message = "상품 가격은 필수 정보 입니다.")
    @Min(value = 0, message = "가격은 0보다 커야 합니다.")
    private BigInteger price;
}
