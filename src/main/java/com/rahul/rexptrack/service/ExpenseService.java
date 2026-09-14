package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.ExpenseRequest;
import com.rahul.rexptrack.dto.response.ExpenseResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {
    List<ExpenseResponse> getAllExpenses(Long userId);
    ExpenseResponse getExpenseById(Long id, Long userId);
    ExpenseResponse createExpense(ExpenseRequest request, Long userId);
    ExpenseResponse updateExpense(Long id, ExpenseRequest request, Long userId);
    void deleteExpense(Long id, Long userId);
    BigDecimal getTotalForMonth(Long userId, int month, int year);
}
