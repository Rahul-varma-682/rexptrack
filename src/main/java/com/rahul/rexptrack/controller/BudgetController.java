package com.rahul.rexptrack.controller;

import com.rahul.rexptrack.dto.request.BudgetRequest;
import com.rahul.rexptrack.dto.response.ApiResponse;
import com.rahul.rexptrack.dto.response.BudgetResponse;
import com.rahul.rexptrack.service.BudgetService;
import com.rahul.rexptrack.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController {
    private final BudgetService budgetService;
    private final SecurityUtil securityUtil;

    @GetMapping("/current") public ResponseEntity<ApiResponse<List<BudgetResponse>>> current() { return ResponseEntity.ok(ApiResponse.success("Current budgets retrieved", budgetService.getCurrentMonthBudgets(securityUtil.getCurrentUserId()))); }
    @PostMapping public ResponseEntity<ApiResponse<BudgetResponse>> create(@Valid @RequestBody BudgetRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Budget created", budgetService.createBudget(request, securityUtil.getCurrentUserId()))); }
    @PutMapping("/{id}") public ResponseEntity<ApiResponse<BudgetResponse>> update(@PathVariable Long id, @Valid @RequestBody BudgetRequest request) { return ResponseEntity.ok(ApiResponse.success("Budget updated", budgetService.updateBudget(id, request, securityUtil.getCurrentUserId()))); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { budgetService.deleteBudget(id, securityUtil.getCurrentUserId()); return ResponseEntity.noContent().build(); }

    public BudgetController(BudgetService budgetService, SecurityUtil securityUtil) {
        this.budgetService = budgetService;
        this.securityUtil = securityUtil;
    }
}


