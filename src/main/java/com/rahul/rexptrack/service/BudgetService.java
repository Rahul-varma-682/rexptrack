package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.BudgetRequest;
import com.rahul.rexptrack.dto.response.BudgetResponse;

import java.util.List;

public interface BudgetService {
    List<BudgetResponse> getCurrentMonthBudgets(Long userId);
    BudgetResponse createBudget(BudgetRequest request, Long userId);
    BudgetResponse updateBudget(Long id, BudgetRequest request, Long userId);
    void deleteBudget(Long id, Long userId);
}
