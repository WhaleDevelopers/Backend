package com.kim.devstu.v1.controller;

import com.kim.devstu.service.InterviewQuestionsService;
import com.kim.devstu.v1.dto.request.AddInterviewQuestionRequestDto;
import com.kim.devstu.v1.dto.response.AddInterviewQuestionResponseDto;
import com.kim.devstu.v1.dto.response.InterviewQuestionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/interview-questions")
@RequiredArgsConstructor
@Tag(name = "Interview Questions", description = "면접 질문 관리 API")
public class InterviewQuestionController {

    private final InterviewQuestionsService interviewQuestionsService;

    @Operation(
            summary = "카테고리별 제한된 개수의 면접 질문 조회",
            description = "GET /api/v1/interview-questions/category-size?categoryId=1&size=5"
    )
    @GetMapping("/category-size")
    public ResponseEntity<List<InterviewQuestionResponseDto>> getQuestionsByCategoryWithSize(
            @Parameter(description = "카테고리 ID") @RequestParam(required = false) String categoryId,
            @Parameter(description = "조회할 질문 개수") @RequestParam(defaultValue = "5") int size) {

        log.info("카테고리 {} 의 면접 질문을 {}개 조회 요청", categoryId, size);

        List<InterviewQuestionResponseDto> questionList = interviewQuestionsService.findRandomQuestionsByCategoryWithSize(categoryId, size);
        System.out.println(questionList.toString());
        return ResponseEntity.ok(questionList);
    }


    @Operation(summary = "면접 질문 추가", description = "POST /api/v1/interview-questions - 인터뷰 질문 추가")
    @PostMapping
    public ResponseEntity<AddInterviewQuestionResponseDto> addInterviewQuestion(@RequestBody AddInterviewQuestionRequestDto req) {
        log.info("새 면접 질문 추가 요청: categoryId={}, question={}", req.getCategoryId(), req.getQuestion());

        AddInterviewQuestionResponseDto saved = interviewQuestionsService.addInterviewQuestion(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
