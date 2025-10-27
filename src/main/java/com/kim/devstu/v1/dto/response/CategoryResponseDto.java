package com.kim.devstu.v1.dto.response;

import com.kim.devstu.model.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record CategoryResponseDto(
    String id,
    String name,
    String displayName,
    Integer sortOrder,
    Boolean isActive,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Integer count
) {
    public static CategoryResponseDto fromEntity(Category category) {
        return new CategoryResponseDto(
                category.getId().toString(),
                category.getName(),
                category.getDisplayName(),
                category.getSortOrder(),
                category.getIsActive(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getCount()
        );
    }

    public static List<CategoryResponseDto> toDtoList(List<Category> categories) {
        return categories.stream()
                .map(CategoryResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
