package com.rahul.rexptrack.controller;

import com.rahul.rexptrack.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai")
public class AiController {
    @PostMapping("/chat")
    public ResponseEntity<ApiResponse<String>> chat() { return ResponseEntity.ok(ApiResponse.success("AI response", "AI service coming soon!")); }

    @GetMapping("/insights")
    public ResponseEntity<ApiResponse<String>> insights() { return ResponseEntity.ok(ApiResponse.success("Insights", "AI insights coming soon!")); }
}
