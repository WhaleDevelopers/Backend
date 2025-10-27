package com.kim.devstu.v1.dto.response;

import com.kim.devstu.model.InterviewQuestion;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterviewQuestionResponseDto {

    @Field("_id") private String id;
    private String categoryId;
    private String categoryDisplayName; //Category 조인 시 포함
    private String question;
    private String answer;
    private String difficulty;
    private List<String> tags;
    private Integer usageCount;
    private LocalDateTime lastUsedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* Entity를 DTO로 변환하는 정적 메서드 */
    public static InterviewQuestionResponseDto toDto(InterviewQuestion entity) {
        if (entity == null) return null;

        return InterviewQuestionResponseDto.builder()
                .id(entity.getId() != null ? entity.getId().toString() : null)
                .categoryId(entity.getCategoryId() != null ? entity.getCategoryId().toString() : null)
                .categoryDisplayName(null) // 조인 시에는 별도로 설정
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

    /* Entity 리스트를 DTO 리스트로 변환하는 정적 메서드 */
    public static List<InterviewQuestionResponseDto> toDtoList(List<InterviewQuestion> entities) {
        if (entities == null) return null;

        return entities.stream()
                .map(InterviewQuestionResponseDto::toDto)
                .collect(Collectors.toList());
    }
}
