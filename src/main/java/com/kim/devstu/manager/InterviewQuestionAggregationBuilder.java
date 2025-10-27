package com.kim.devstu.manager;

import io.netty.util.internal.StringUtil;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class InterviewQuestionAggregationBuilder {

    /**
     * 면접 질문 랜덤 조회를 위한 집계 파이프라인 생성 (카테고리 조인 포함)
     * @param categoryId 카테고리 ID (null인 경우 모든 카테고리)
     * @param size 조회할 개수
     * @return 집계 파이프라인
     */
    public Aggregation buildRandomQuestionsWithCategoryAggregation(ObjectId categoryId, int size) {
        List<AggregationOperation> pipeline = new ArrayList<>();

        // 1. 기본 필터: 활성 상태
        pipeline.add(Aggregation.match(Criteria.where("is_active").is(true)));

        // 2. 카테고리 필터
        if (categoryId != null)  pipeline.add(Aggregation.match(Criteria.where("category_id").is(categoryId)));

        // 3. 카테고리 조인 ($lookup)
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("categories")
                .localField("category_id")
                .foreignField("_id")
                .as("categoryInfo");
        pipeline.add(lookupOperation);

        // 4. 조인된 카테고리 정보 언패킹 - 올바릅니다.
        pipeline.add(Aggregation.unwind("categoryInfo", true));

        pipeline.add(Aggregation.project()
//                .andExclude("_id") // 기존 _id는 제외하고 새로 id를 만듭니다.
//                .andExpression("_id").as("id") // DB의 _id 필드를 DTO의 id 필드로 매핑
                .and("category_id").as("categoryId")
                .and("categoryInfo.display_name").as("categoryDisplayName")
                .and("question").as("question")
                .and("answer").as("answer")
                .and("difficulty").as("difficulty")
                .and("tags").as("tags")
                .and("usage_count").as("usageCount")
                .and("last_used_at").as("lastUsedAt")
                .and("created_at").as("createdAt")
                .and("updated_at").as("updatedAt")
        );

        pipeline.add(Aggregation.sample(size));

        return Aggregation.newAggregation(pipeline);
    }

    /**
     * 면접 질문 랜덤 조회를 위한 집계 파이프라인 생성
     * @param categoryId 카테고리 ID (null인 경우 모든 카테고리)
     * @param size 조회할 개수
     * @return 집계 파이프라인
     */
    public Aggregation buildRandomQuestionsAggregation(String categoryId, int size) {
        List<AggregationOperation> pipeline = new ArrayList<>();

        // 1. 기본 필터: 활성 상태
        pipeline.add(Aggregation.match(Criteria.where("isActive").is(true)));
        // 2. 카테고리 필터
        if (categoryId != null) {
            pipeline.add(Aggregation.match(Criteria.where("categoryId").is(categoryId)));
        }

        // 3. 랜덤 샘플링
        pipeline.add(Aggregation.sample(size));

        return Aggregation.newAggregation(pipeline);
    }

    /**
     * 면접 질문 난이도별 조회를 위한 집계 파이프라인 생성
     * @param categoryId 카테고리 ID
     * @param difficulty 난이도
     * @param size 조회할 개수
     * @return 집계 파이프라인
     */
    public Aggregation buildQuestionsByDifficultyAggregation(String categoryId, String difficulty, int size) {
        List<AggregationOperation> pipeline = new ArrayList<>();

        // 1. 기본 필터: 활성 상태
        pipeline.add(Aggregation.match(Criteria.where("isActive").is(true)));
        // 2. 카테고리 필터
        if (categoryId != null) {
            pipeline.add(Aggregation.match(Criteria.where("categoryId").is(categoryId)));
        }
        // 3. 난이도 필터
        if (difficulty != null && !difficulty.isEmpty()) {
            pipeline.add(Aggregation.match(Criteria.where("difficulty").is(difficulty)));
        }

        // 5. 랜덤 샘플링
        pipeline.add(Aggregation.sample(size));

        return Aggregation.newAggregation(pipeline);
    }

    /**
     * 태그별 질문 조회를 위한 집계 파이프라인 생성
     * @param tags 태그 리스트
     * @param size 조회할 개수
     * @return 집계 파이프라인
     */
    public Aggregation buildQuestionsByTagsAggregation(List<String> tags, int size) {
        List<AggregationOperation> pipeline = new ArrayList<>();

        // 1. 기본 필터: 활성 상태
        pipeline.add(Aggregation.match(Criteria.where("isActive").is(true)));
        // 2. 태그 필터
        if (tags != null && !tags.isEmpty()) {
            pipeline.add(Aggregation.match(Criteria.where("tags").in(tags)));
        }

        // 3. 랜덤 샘플링
        pipeline.add(Aggregation.sample(size));

        return Aggregation.newAggregation(pipeline);
    }
}
