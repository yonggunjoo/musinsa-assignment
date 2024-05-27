package com.musinsa.domain.brand.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchBrandRequest {
    @NotNull(message = "상품 ID는 필수 요청 정보 입니다.")
    @Min(value = 1, message = "수정할 상품 id는 0보다 커야합니다.")
    private Long id;
    @NotBlank(message = "brand 명 값이 비어있습니다.")
    private String brandName;
}
