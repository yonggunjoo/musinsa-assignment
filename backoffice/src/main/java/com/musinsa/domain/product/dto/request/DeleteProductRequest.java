package com.musinsa.domain.product.dto.request;

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
public class DeleteProductRequest {
    @NotNull(message = "상품 ID는 필수 요청 정보 입니다.")
    @Min(value = 1, message = "삭제할 상품 id는 0보다 커야합니다.")
    private Long id;
}
