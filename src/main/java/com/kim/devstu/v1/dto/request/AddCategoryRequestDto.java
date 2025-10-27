package com.kim.devstu.v1.dto.request;

public record AddCategoryRequestDto(
    String name,
    String displayName,
    Integer sortOrder,
    Boolean isActive
) { }
