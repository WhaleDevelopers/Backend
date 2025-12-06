package com.kim.devstu.v1.mapper;

import com.kim.devstu.model.InterviewQuestion;
import com.kim.devstu.v1.dto.request.AddInterviewQuestionRequestDto;

public class InterviewQuestionMapper {

    public static InterviewQuestion toEntity(AddInterviewQuestionRequestDto dto, InterviewQuestion.CategoryInfo categoryInfo) {
        return InterviewQuestion.builder()
                .category(categoryInfo)
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .difficulty(dto.getDifficulty())
                .tags(dto.getTags())
                .usageCount(0)
                .version(dto.getVersion() != null ? dto.getVersion() : 1)
                .isActive(true)
                .build();
    }
}