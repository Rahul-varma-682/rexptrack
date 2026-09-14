package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.IncomeRequest;
import com.rahul.rexptrack.dto.response.IncomeResponse;

import java.math.BigDecimal;
import java.util.List;

public interface IncomeService {
    List<IncomeResponse> getAllIncomes(Long userId);
    IncomeResponse getIncomeById(Long id, Long userId);
    IncomeResponse createIncome(IncomeRequest request, Long userId);
    IncomeResponse updateIncome(Long id, IncomeRequest request, Long userId);
    void deleteIncome(Long id, Long userId);
    BigDecimal getTotalForMonth(Long userId, int month, int year);
}
