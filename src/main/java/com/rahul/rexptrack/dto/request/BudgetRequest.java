package com.rahul.rexptrack.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class BudgetRequest {

    @NotNull
    private Long categoryId;

    @NotNull @Positive
    private BigDecimal amount;

    @NotNull @Min(1) @Max(12)
    private Integer month;

    @NotNull
    private Integer year;

    private Integer alertAtPercentage = 80;

    public BudgetRequest() {}

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getAlertAtPercentage() { return alertAtPercentage; }
    public void setAlertAtPercentage(Integer alertAtPercentage) { this.alertAtPercentage = alertAtPercentage; }
}