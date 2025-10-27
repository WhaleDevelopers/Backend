package com.kim.devstu.repository;

import com.kim.devstu.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    // 활성화된 카테고리를 정렬 순서대로 조회
    @Query("{ 'is_active': true }")
    List<Category> findByIsActiveTrueOrderBySortOrderAsc();

    // 이름으로 카테고리 조회
    Category findByName(String name);

}
