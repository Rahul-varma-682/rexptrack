package com.rahul.rexptrack.controller;

import com.rahul.rexptrack.dto.request.IncomeRequest;
import com.rahul.rexptrack.dto.response.ApiResponse;
import com.rahul.rexptrack.dto.response.IncomeResponse;
import com.rahul.rexptrack.service.IncomeService;
import com.rahul.rexptrack.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incomes")
public class IncomeController {
    private final IncomeService incomeService;
    private final SecurityUtil securityUtil;

    @GetMapping public ResponseEntity<ApiResponse<List<IncomeResponse>>> getAll() { return ResponseEntity.ok(ApiResponse.success("Incomes retrieved", incomeService.getAllIncomes(securityUtil.getCurrentUserId()))); }
    @GetMapping("/{id}") public ResponseEntity<ApiResponse<IncomeResponse>> getById(@PathVariable Long id) { return ResponseEntity.ok(ApiResponse.success("Income retrieved", incomeService.getIncomeById(id, securityUtil.getCurrentUserId()))); }
    @PostMapping public ResponseEntity<ApiResponse<IncomeResponse>> create(@Valid @RequestBody IncomeRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Income created", incomeService.createIncome(request, securityUtil.getCurrentUserId()))); }
    @PutMapping("/{id}") public ResponseEntity<ApiResponse<IncomeResponse>> update(@PathVariable Long id, @Valid @RequestBody IncomeRequest request) { return ResponseEntity.ok(ApiResponse.success("Income updated", incomeService.updateIncome(id, request, securityUtil.getCurrentUserId()))); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { incomeService.deleteIncome(id, securityUtil.getCurrentUserId()); return ResponseEntity.noContent().build(); }

    public IncomeController(IncomeService incomeService, SecurityUtil securityUtil) {
        this.incomeService = incomeService;
        this.securityUtil = securityUtil;
    }
}


