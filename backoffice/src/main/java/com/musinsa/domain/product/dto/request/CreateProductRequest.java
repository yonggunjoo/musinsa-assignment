package com.musinsa.domain.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "상품 추가 요청 정보")
public class CreateProductRequest {
    @NotNull(message = "브랜드 이름은 필수 정보 입니다.")
    @Schema(description = "브랜드 이름", example = "J", required = true)
    private String brandName;
    @NotNull(message = "카테고리 이름은 필수 정보 입니다.")
    @Schema(description = "카테고리 이름", example = "상의", required = true)
    private String categoryName;
    @NotNull(message = "상품 가격은 필수 정보 입니다.")
    @Min(value = 0, message = "가격은 0보다 커야 합니다.")
    @Schema(description = "상품 가격", example = "1", required = true)
    private BigInteger price;
}
