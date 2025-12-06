package com.kim.devstu.v1.dto.request; // 패키지명 확인

import lombok.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddInterviewQuestionRequestDto {
    private String categoryId;

    private String question;
    private String answer;

    @Builder.Default
    private String difficulty = "etc";

    private List<String> tags;

    @Builder.Default
    private Boolean isActive = true;

    private Integer version;
}