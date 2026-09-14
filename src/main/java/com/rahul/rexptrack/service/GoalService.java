package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.GoalRequest;
import com.rahul.rexptrack.dto.response.GoalResponse;

import java.math.BigDecimal;
import java.util.List;

public interface GoalService {
    List<GoalResponse> getAllGoals(Long userId);
    GoalResponse createGoal(GoalRequest request, Long userId);
    GoalResponse contributeToGoal(Long id, BigDecimal amount, Long userId);
    void deleteGoal(Long id, Long userId);
}
