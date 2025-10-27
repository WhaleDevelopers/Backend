package com.kim.devstu.v1.dto.response;

import com.kim.devstu.model.InterviewQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddInterviewQuestionResponseDto {
    private ObjectId categoryId;
    private String question;
    private String answer;
    private String difficulty ;
    private List<String> tags;
    private Integer version;
    private Boolean isActive;

    public static AddInterviewQuestionResponseDto fromEntity(InterviewQuestion entity) {
        return AddInterviewQuestionResponseDto.builder()
                .categoryId(entity.getCategoryId())
                .question(entity.getQuestion())
                .answer(entity.getAnswer())
                .difficulty(entity.getDifficulty())
                .tags(entity.getTags())
                .version(entity.getVersion())
                .isActive(entity.getIsActive())
                .build();
    }
}


