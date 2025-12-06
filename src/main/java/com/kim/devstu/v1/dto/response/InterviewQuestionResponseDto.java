package com.kim.devstu.v1.dto.response;

import com.kim.devstu.model.InterviewQuestion;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InterviewQuestionResponseDto {

    private String id;
    private String categoryId;
    private String categoryDisplayName;
    private String question;
    private String answer;
    private String difficulty;
    private List<String> tags;
    private Integer usageCount;
    private LocalDateTime lastUsedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static InterviewQuestionResponseDto toDto(InterviewQuestion entity) {
        if (entity == null) return null;

        InterviewQuestion.CategoryInfo categoryInfo = entity.getCategory();

        return InterviewQuestionResponseDto.builder()
                .id(entity.getId() != null ? entity.getId().toString() : null)

                .categoryId(categoryInfo != null && categoryInfo.getId() != null
                        ? categoryInfo.getId().toString()
                        : null)

                .categoryDisplayName(categoryInfo != null ? categoryInfo.getDisplayName() : null)

                .question(entity.getQuestion())
                .answer(entity.getAnswer())
                .difficulty(entity.getDifficulty())
                .tags(entity.getTags())
                .usageCount(entity.getUsageCount())
                .lastUsedAt(entity.getLastUsedAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static List<InterviewQuestionResponseDto> toDtoList(List<InterviewQuestion> entities) {
        if (entities == null) return null;

        return entities.stream()
                .map(InterviewQuestionResponseDto::toDto)
                .collect(Collectors.toList());
    }
}