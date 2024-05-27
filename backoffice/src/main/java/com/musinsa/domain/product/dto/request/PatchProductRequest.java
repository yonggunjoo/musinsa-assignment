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
@Schema(description = "상품 수정 요청 정보")
public class PatchProductRequest {
    @NotNull(message = "상품 ID는 필수 요청 정보 입니다.")
    @Min(value = 1, message = "수정할 상품 ID는 0보다 커야합니다.")
    @Schema(description = "상품 ID", example = "J", required = true)
    private Long id;
    @Schema(description = "브랜드 이름", example = "J")
    private String brandName;
    @Schema(description = "카테고리 이름", example = "상의")
    private String categoryName;
    @Min(value = 0, message = "가격은 0보다 커야 합니다.")
    @Schema(description = "상품 가격", example = "1")
    private BigInteger price;
}
