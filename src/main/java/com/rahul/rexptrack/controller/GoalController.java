package com.rahul.rexptrack.controller;

import com.rahul.rexptrack.dto.request.GoalRequest;
import com.rahul.rexptrack.dto.response.ApiResponse;
import com.rahul.rexptrack.dto.response.GoalResponse;
import com.rahul.rexptrack.service.GoalService;
import com.rahul.rexptrack.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {
    private final GoalService goalService;
    private final SecurityUtil securityUtil;

    @GetMapping public ResponseEntity<ApiResponse<List<GoalResponse>>> getAll() { return ResponseEntity.ok(ApiResponse.success("Goals retrieved", goalService.getAllGoals(securityUtil.getCurrentUserId()))); }
    @PostMapping public ResponseEntity<ApiResponse<GoalResponse>> create(@Valid @RequestBody GoalRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Goal created", goalService.createGoal(request, securityUtil.getCurrentUserId()))); }
    @PutMapping("/{id}/contribute") public ResponseEntity<ApiResponse<GoalResponse>> contribute(@PathVariable Long id, @RequestParam BigDecimal amount) { return ResponseEntity.ok(ApiResponse.success("Contribution added", goalService.contributeToGoal(id, amount, securityUtil.getCurrentUserId()))); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { goalService.deleteGoal(id, securityUtil.getCurrentUserId()); return ResponseEntity.noContent().build(); }

    public GoalController(GoalService goalService, SecurityUtil securityUtil) {
        this.goalService = goalService;
        this.securityUtil = securityUtil;
    }
}


