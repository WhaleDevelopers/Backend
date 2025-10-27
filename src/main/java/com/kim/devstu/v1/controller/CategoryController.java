package com.kim.devstu.v1.controller;

import com.kim.devstu.model.Category;
import com.kim.devstu.service.CategoryService;
import com.kim.devstu.v1.dto.request.AddCategoryRequestDto;
import com.kim.devstu.v1.dto.request.AddCategoryRequestDto;
import com.kim.devstu.v1.dto.response.CategoryResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "카테고리 관리 API")
public class CategoryController {

    private final CategoryService categoryService;

    // TODO 추후 삭제
    @Operation(summary = "카테고리 목록 조회 (DB조회)")
    @GetMapping("/active")
    public ResponseEntity<List<CategoryResponseDto>> getActiveCategories() {
        log.info("카테고리 목록 요청");

        List<CategoryResponseDto> categories = categoryService.getActiveCategories();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "카테고리 목록 조회 (DB조회)")
    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(
            @RequestParam(required = false) Boolean isActive
    ) {
        log.info("카테고리 목록 요청");

        List<CategoryResponseDto> categories = categoryService.getCategories(isActive);
        return ResponseEntity.ok(categories);
    }


    @Operation(summary = "카테고리 추가")
    @PostMapping("")
    public ResponseEntity<CategoryResponseDto> addCategory(
            @RequestBody AddCategoryRequestDto categoryDto
    ) {
        log.info("카테고리 추가 요청 : {}",categoryDto);

        CategoryResponseDto categories = categoryService.addCategory(categoryDto);
        return ResponseEntity.ok(categories);
    }
}
