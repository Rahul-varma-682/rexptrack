package com.rahul.rexptrack.controller;

import com.rahul.rexptrack.dto.request.ExpenseRequest;
import com.rahul.rexptrack.dto.response.ApiResponse;
import com.rahul.rexptrack.dto.response.ExpenseResponse;
import com.rahul.rexptrack.service.ExpenseService;
import com.rahul.rexptrack.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final SecurityUtil securityUtil;

    @GetMapping public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getAll() { return ResponseEntity.ok(ApiResponse.success("Expenses retrieved", expenseService.getAllExpenses(securityUtil.getCurrentUserId()))); }
    @GetMapping("/{id}") public ResponseEntity<ApiResponse<ExpenseResponse>> getById(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success("Expense retrieved", expenseService.getExpenseById(id, securityUtil.getCurrentUserId()))); }
    @PostMapping public ResponseEntity<ApiResponse<ExpenseResponse>> create(@Valid @RequestBody ExpenseRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Expense created", expenseService.createExpense(request, securityUtil.getCurrentUserId()))); }
    @PutMapping("/{id}") public ResponseEntity<ApiResponse<ExpenseResponse>> update(@PathVariable Long id, @Valid @RequestBody ExpenseRequest request) { return ResponseEntity.ok(ApiResponse.success("Expense updated", expenseService.updateExpense(id, request, securityUtil.getCurrentUserId()))); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { expenseService.deleteExpense(id, securityUtil.getCurrentUserId()); return ResponseEntity.noContent().build(); }

    public ExpenseController(ExpenseService expenseService, SecurityUtil securityUtil) {
        this.expenseService = expenseService;
        this.securityUtil = securityUtil;
    }
}


