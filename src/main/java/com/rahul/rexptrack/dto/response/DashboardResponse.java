package com.rahul.rexptrack.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class DashboardResponse {
    private BigDecimal totalIncomeThisMonth;
    private BigDecimal totalExpensesThisMonth;
    private BigDecimal netBalance;
    private Double savingsRate;
    private List<ExpenseResponse> recentExpenses;
    private List<BudgetResponse> activeBudgets;
    private List<GoalResponse> activeGoals;
    private String aiInsight = "Your finances look stable this month.";

    public DashboardResponse() {}

    public DashboardResponse(BigDecimal totalIncomeThisMonth, BigDecimal totalExpensesThisMonth,
                             BigDecimal netBalance, Double savingsRate,
                             List<ExpenseResponse> recentExpenses, List<BudgetResponse> activeBudgets,
                             List<GoalResponse> activeGoals) {
        this.totalIncomeThisMonth = totalIncomeThisMonth;
        this.totalExpensesThisMonth = totalExpensesThisMonth;
        this.netBalance = netBalance;
        this.savingsRate = savingsRate;
        this.recentExpenses = recentExpenses;
        this.activeBudgets = activeBudgets;
        this.activeGoals = activeGoals;
    }

    public BigDecimal getTotalIncomeThisMonth() { return totalIncomeThisMonth; }
    public void setTotalIncomeThisMonth(BigDecimal totalIncomeThisMonth) { this.totalIncomeThisMonth = totalIncomeThisMonth; }
    public BigDecimal getTotalExpensesThisMonth() { return totalExpensesThisMonth; }
    public void setTotalExpensesThisMonth(BigDecimal totalExpensesThisMonth) { this.totalExpensesThisMonth = totalExpensesThisMonth; }
    public BigDecimal getNetBalance() { return netBalance; }
    public void setNetBalance(BigDecimal netBalance) { this.netBalance = netBalance; }
    public Double getSavingsRate() { return savingsRate; }
    public void setSavingsRate(Double savingsRate) { this.savingsRate = savingsRate; }
    public List<ExpenseResponse> getRecentExpenses() { return recentExpenses; }
    public void setRecentExpenses(List<ExpenseResponse> recentExpenses) { this.recentExpenses = recentExpenses; }
    public List<BudgetResponse> getActiveBudgets() { return activeBudgets; }
    public void setActiveBudgets(List<BudgetResponse> activeBudgets) { this.activeBudgets = activeBudgets; }
    public List<GoalResponse> getActiveGoals() { return activeGoals; }
    public void setActiveGoals(List<GoalResponse> activeGoals) { this.activeGoals = activeGoals; }
    public String getAiInsight() { return aiInsight; }
    public void setAiInsight(String aiInsight) { this.aiInsight = aiInsight; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private BigDecimal totalIncomeThisMonth;
        private BigDecimal totalExpensesThisMonth;
        private BigDecimal netBalance;
        private Double savingsRate;
        private List<ExpenseResponse> recentExpenses;
        private List<BudgetResponse> activeBudgets;
        private List<GoalResponse> activeGoals;
        private String aiInsight = "Your finances look stable this month.";

        public Builder totalIncomeThisMonth(BigDecimal v) { this.totalIncomeThisMonth = v; return this; }
        public Builder totalExpensesThisMonth(BigDecimal v) { this.totalExpensesThisMonth = v; return this; }
        public Builder netBalance(BigDecimal v) { this.netBalance = v; return this; }
        public Builder savingsRate(Double v) { this.savingsRate = v; return this; }
        public Builder recentExpenses(List<ExpenseResponse> v) { this.recentExpenses = v; return this; }
        public Builder activeBudgets(List<BudgetResponse> v) { this.activeBudgets = v; return this; }
        public Builder activeGoals(List<GoalResponse> v) { this.activeGoals = v; return this; }
        public Builder aiInsight(String v) { this.aiInsight = v; return this; }

        public DashboardResponse build() {
            DashboardResponse r = new DashboardResponse(
                totalIncomeThisMonth, totalExpensesThisMonth, netBalance,
                savingsRate, recentExpenses, activeBudgets, activeGoals);
            r.setAiInsight(aiInsight);
            return r;
        }
    }
}