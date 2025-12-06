package com.kim.devstu.manager;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InterviewQuestionAggregationBuilder {

    // 면접 질문 랜덤 조회
    public Aggregation buildRandomQuestionsAggregation(String categoryId, int size) {
        List<AggregationOperation> pipeline = new ArrayList<>();

        pipeline.add(Aggregation.match(Criteria.where("is_active").is(true)));

        if (categoryId != null) {
            pipeline.add(Aggregation.match(Criteria.where("category._id").is(new ObjectId(categoryId))));
        }

        pipeline.add(Aggregation.sample(size));

        return Aggregation.newAggregation(pipeline);
    }


}