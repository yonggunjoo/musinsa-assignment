package com.musinsa.domain.category.dto;

import com.musinsa.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "카테고리 정보")
public class CategoryDTO {
    @Schema(description = "카테고리 아이디")
    private Long id;
    @Schema(description = "카테고리 이름")
    private String name;

    public static CategoryDTO convertToCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category convertToCategory(CategoryDTO dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
