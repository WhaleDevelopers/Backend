package com.kim.devstu.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "interview_questions")
@CompoundIndexes({
    @CompoundIndex(name = "categoryId_isActive_idx", def = "{ 'category_id': 1, 'is_active' : -1 }"),
    @CompoundIndex(name = "tags_isActive_idx",       def = "{ 'tags'       : 1, 'is_active' : -1 }")
})
public class InterviewQuestion {

    @Id private ObjectId id;

    @Field("category_id")
    private ObjectId categoryId;

    @Field("question")
    private String question;

    @Field("answer")
    private String answer;

    @Field("difficulty")
    @Builder.Default
    private String difficulty = "etc";

    @Field("tags")
    private List<String> tags;

    @Field("version")
    @Builder.Default
    private Integer version = 1;

    @Field("usage_count")
    @Builder.Default
    private Integer usageCount = 0;

    @Field("last_used_at")
    private LocalDateTime lastUsedAt;

    @Field("is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;
}
