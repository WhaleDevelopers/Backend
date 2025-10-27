package com.kim.devstu.service;

import com.kim.devstu.model.Category;
import com.kim.devstu.repository.CategoryRepository;
import com.kim.devstu.v1.dto.request.AddCategoryRequestDto;
import com.kim.devstu.v1.dto.request.AddCategoryRequestDto;
import com.kim.devstu.v1.dto.response.CategoryResponseDto;
import com.kim.devstu.v1.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /* 활성화된 모든 카테고리를 우선순으로 조회 */
//    @Cacheable(value = "categories", key = "'active-sorted'")
    // TODO 추후 삭제
    public List<CategoryResponseDto> getActiveCategories() {
        log.info("활성화된 카테고리 목록을 조회");

        List<Category> categories = categoryRepository.findByIsActiveTrueOrderBySortOrderAsc();
        return CategoryResponseDto.toDtoList(categories);
    }

    public List<CategoryResponseDto> getCategories(Boolean isActive) {
        List<Category> categories;

        if (Boolean.TRUE.equals(isActive)) {    //Null Check
            log.info("활성화된 카테고리만 조회");
            categories = categoryRepository.findByIsActiveTrueOrderBySortOrderAsc();
        } else {
            log.info("모든 카테고리 조회");
            categories = categoryRepository.findAll();
        }
        return CategoryResponseDto.toDtoList(categories);
    }

    /* 특정 이름의 카테고리 조회 */
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    /* 카테고리 추가 */
    public CategoryResponseDto addCategory(AddCategoryRequestDto categoryDto) {
        Category entity = CategoryMapper.toEntity(categoryDto);
        Category saved = categoryRepository.save(entity);
        return  CategoryResponseDto.fromEntity(saved);
    }

}
