package com.kim.devstu.repository;

import com.kim.devstu.model.InterviewQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionsRepository extends MongoRepository<InterviewQuestion, String> {

}
