package com.musinsa.domain.brand.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandRequest {
    @NotNull(message = "브랜드 이름은 필수 정보 입니다.")
    private String brandName;
}
