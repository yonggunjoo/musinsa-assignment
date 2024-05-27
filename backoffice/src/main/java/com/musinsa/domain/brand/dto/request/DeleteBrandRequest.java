package com.musinsa.domain.brand.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "브랜드 요청 정보")
public class DeleteBrandRequest {
    @NotNull(message = "상품 ID는 필수 요청 정보 입니다.")
    @Min(value = 1, message = "수정할 상품 id는 0보다 커야합니다.")
    @Schema(description = "브랜드 아이디", example = "1", required = true)
    private Long id;

}
