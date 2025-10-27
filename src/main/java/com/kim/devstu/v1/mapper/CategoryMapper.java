package com.kim.devstu.v1.mapper;

import com.kim.devstu.model.Category;
import com.kim.devstu.v1.dto.request.AddCategoryRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static Category toEntity(AddCategoryRequestDto dto) {
        return Category.builder()
                .name        (dto.name())
                .displayName (dto.displayName())
                .sortOrder   (dto.sortOrder())
                .isActive    (dto.isActive())
                .build();
    }
}
