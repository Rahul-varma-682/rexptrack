package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.response.BudgetResponse;
import com.rahul.rexptrack.dto.response.DashboardResponse;
import com.rahul.rexptrack.dto.response.ExpenseResponse;
import com.rahul.rexptrack.dto.response.GoalResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final BudgetService budgetService;
    private final GoalService goalService;

    @Override
    @Transactional(readOnly = true)
    public DashboardResponse getDashboard(Long userId) {
        LocalDate now = LocalDate.now();
        BigDecimal income = incomeService.getTotalForMonth(userId, now.getMonthValue(), now.getYear());
        BigDecimal expenses = expenseService.getTotalForMonth(userId, now.getMonthValue(), now.getYear());
        BigDecimal netBalance = income.subtract(expenses);
        double savingsRate = income.signum() == 0 ? 0D : netBalance.multiply(BigDecimal.valueOf(100)).divide(income, 2, RoundingMode.HALF_UP).doubleValue();
        List<ExpenseResponse> recentExpenses = expenseService.getAllExpenses(userId).stream().limit(5).toList();
        List<BudgetResponse> activeBudgets = budgetService.getCurrentMonthBudgets(userId).stream().filter(budget -> Boolean.TRUE.equals(budget.getIsActive())).toList();
        List<GoalResponse> activeGoals = goalService.getAllGoals(userId).stream().filter(goal -> "ACTIVE".equals(goal.getStatus())).toList();
        DashboardResponse response = new DashboardResponse();
        response.setTotalIncomeThisMonth(income); response.setTotalExpensesThisMonth(expenses); response.setNetBalance(netBalance);
        response.setSavingsRate(savingsRate); response.setRecentExpenses(recentExpenses); response.setActiveBudgets(activeBudgets);
        response.setActiveGoals(activeGoals); response.setAiInsight("Your finances look stable this month.");
        return response;
    }

    public AnalyticsServiceImpl(IncomeService incomeService, ExpenseService expenseService, BudgetService budgetService, GoalService goalService) {
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.budgetService = budgetService;
        this.goalService = goalService;
    }
}

