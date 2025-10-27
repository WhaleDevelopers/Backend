package com.kim.devstu.v1.controller;

import com.kim.devstu.enums.Difficulty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/preset")
@RequiredArgsConstructor
@Tag(name = "Preset", description = "사전 정의된 설정값 조회 API")
public class PresetController {


    @Operation(summary = "문제 난이도 목록 조회")
    @GetMapping("/questions-difficulties")
    public ResponseEntity<List<Difficulty>> getDifficulties() {

        log.info("문제 난이도 목록 요청");

        List<Difficulty> difficulties = Arrays.asList(Difficulty.values());
        return ResponseEntity.ok(difficulties);
    }



}
