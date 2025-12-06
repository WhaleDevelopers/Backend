package com.kim.devstu.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
@CompoundIndexes({
        @CompoundIndex(name = "isActive_sortOrder_desc_idx", def = "{ 'is_active': -1, 'sort_order': 1 }")
})
public class Category {

    @Id private ObjectId id;

    @Indexed(unique = true)
    private String name;

    @Field("display_name")
    private String displayName;

    @Field("sort_order")
    private Integer sortOrder;

    @Field("is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Builder.Default
    private Integer count = 0;
}