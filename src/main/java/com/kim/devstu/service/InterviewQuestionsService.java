package com.kim.devstu.service;

import com.kim.devstu.manager.InterviewQuestionAggregationBuilder;
import com.kim.devstu.model.InterviewQuestion;
import com.kim.devstu.repository.InterviewQuestionsRepository;
import com.kim.devstu.v1.dto.request.AddInterviewQuestionRequestDto;
import com.kim.devstu.v1.dto.response.AddInterviewQuestionResponseDto;
import com.kim.devstu.v1.dto.response.InterviewQuestionResponseDto;
import com.kim.devstu.v1.mapper.InterviewQuestionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterviewQuestionsService {

    private final InterviewQuestionsRepository interviewQuestionsRepository;
    private final MongoTemplate mongoTemplate;
    private final InterviewQuestionAggregationBuilder interviewQuestionAggregationBuilder;

    /* 카테고리와 개수로 랜덤 질문 조회 (카테고리 displayName 포함) */
//    @Cacheable(value = "interview-questions", key = "'category-' + #categoryId + '-size-' + #size + '-random-with-category'")
    public List<InterviewQuestionResponseDto> findRandomQuestionsByCategoryWithSize(ObjectId categoryId, int size) {
        log.info("카테고리 {}의 랜덤 면접 질문을 {}개 조회합니다. (카테고리 정보 포함)", categoryId, size);

        if (categoryId == null)  throw new IllegalArgumentException("카테고리를 선택해주세요.");

        Aggregation aggregation = interviewQuestionAggregationBuilder.buildRandomQuestionsWithCategoryAggregation(categoryId, size);

        return mongoTemplate.aggregate(
                aggregation,
                "interview_questions",
                InterviewQuestionResponseDto.class
        ).getMappedResults();
    }

    /**
     * Create and save InterviewQuestion from AddInterviewQuestionRequestDto (API usage)
     */
    public AddInterviewQuestionResponseDto addInterviewQuestion(AddInterviewQuestionRequestDto req) {
        InterviewQuestion entity  = InterviewQuestionMapper.toEntity(req);
        InterviewQuestion saved = interviewQuestionsRepository.save(entity);
        return AddInterviewQuestionResponseDto.fromEntity(saved);
    }
}