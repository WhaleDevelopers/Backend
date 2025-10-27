package com.kim.devstu.v1.mapper;

import com.kim.devstu.model.InterviewQuestion;
import com.kim.devstu.v1.dto.request.AddInterviewQuestionRequestDto;
import org.springframework.stereotype.Component;

@Component
public class InterviewQuestionMapper {

    public static InterviewQuestion toEntity(AddInterviewQuestionRequestDto dto) {
        return InterviewQuestion.builder()
                .categoryId(dto.getCategoryId())
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .difficulty(dto.getDifficulty())
                .tags(dto.getTags())
                .version(dto.getVersion())
                .isActive(dto.getIsActive())
                .build();
    }

}
