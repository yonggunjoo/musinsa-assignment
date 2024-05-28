package com.musinsa.domain.brand.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "브랜드 추가 요청 정보")
public class CreateBrandRequest {
    @NotNull(message = "브랜드 이름은 필수 정보 입니다.")
    @Schema(description = "브랜드 이름", example = "J", required = true)
    private String brandName;
}
