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
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterviewQuestionsService {

    private final InterviewQuestionsRepository interviewQuestionsRepository;
    private final MongoTemplate mongoTemplate;
    private final InterviewQuestionAggregationBuilder aggregationBuilder;

    private final CategoryService categoryService;

    /* 카테고리와 개수로 랜덤 질문 조회 */
    public List<InterviewQuestionResponseDto> findRandomQuestionsByCategoryWithSize(String categoryId, int size) {
        log.info("카테고리 {} 랜덤 질문 {}개 조회", categoryId, size);

        Aggregation aggregation = aggregationBuilder.buildRandomQuestionsAggregation(categoryId, size);

        List<InterviewQuestion> entities = mongoTemplate.aggregate(
                aggregation,
                "interview_questions",
                InterviewQuestion.class
        ).getMappedResults();

        return InterviewQuestionResponseDto.toDtoList(entities);
    }

    // 질문 추가
    public AddInterviewQuestionResponseDto addInterviewQuestion(AddInterviewQuestionRequestDto req) {
        InterviewQuestion.CategoryInfo categoryInfo = categoryService.getCategoryInfoById(new ObjectId(req.getCategoryId()));

        InterviewQuestion entity = InterviewQuestionMapper.toEntity(req, categoryInfo);

        InterviewQuestion saved = interviewQuestionsRepository.save(entity);

        log.info("질문 저장 완료. ID: {}, Category: {}", saved.getId(), saved.getCategory().getDisplayName());

        return AddInterviewQuestionResponseDto.fromEntity(saved);
    }
}