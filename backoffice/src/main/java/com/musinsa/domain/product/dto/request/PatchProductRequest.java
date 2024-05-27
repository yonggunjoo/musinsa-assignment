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
public class PatchProductRequest {
    @NotNull(message = "브랜드 ID는 필수 요청 정보 입니다.")
    @Min(value = 1, message = "수정할 브랜드 ID는 0보다 커야합니다.")
    private Long id;
    private String brandName;
    private String categoryName;
    @Min(value = 0, message = "가격은 0보다 커야 합니다.")
    private BigInteger price;
}
