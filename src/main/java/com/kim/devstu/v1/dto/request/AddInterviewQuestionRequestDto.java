package com.kim.devstu.v1.dto.request;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddInterviewQuestionRequestDto {
    private ObjectId categoryId;
    private String question;
    private String answer;
    private String difficulty = "etc";
    private List<String> tags;
    private Integer version = 1;
    private Boolean isActive = true;
}
