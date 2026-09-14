package com.rahul.rexptrack.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;
public class BudgetResponse {
    private Long id;
    private BigDecimal amount;
    private BigDecimal spentAmount;
    private Integer month;
    private Integer year;
    private Integer alertAtPercentage;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private Long categoryId;
    private String categoryName;
    private String categoryIcon;
    private BigDecimal remainingAmount;
    private int usagePercentage;

    public BudgetResponse() {
    }

    public BudgetResponse(Long id, BigDecimal amount, BigDecimal spentAmount, Integer month, Integer year, Integer alertAtPercentage, Boolean isActive, LocalDateTime createdAt, Long categoryId, String categoryName, String categoryIcon, BigDecimal remainingAmount, int usagePercentage) {
        this.id = id;
        this.amount = amount;
        this.spentAmount = spentAmount;
        this.month = month;
        this.year = year;
        this.alertAtPercentage = alertAtPercentage;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.remainingAmount = remainingAmount;
        this.usagePercentage = usagePercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAlertAtPercentage() {
        return alertAtPercentage;
    }

    public void setAlertAtPercentage(Integer alertAtPercentage) {
        this.alertAtPercentage = alertAtPercentage;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public int getUsagePercentage() {
        return usagePercentage;
    }

    public void setUsagePercentage(int usagePercentage) {
        this.usagePercentage = usagePercentage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private BigDecimal amount;
        private BigDecimal spentAmount;
        private Integer month;
        private Integer year;
        private Integer alertAtPercentage;
        private Boolean isActive;
        private LocalDateTime createdAt;
        private Long categoryId;
        private String categoryName;
        private String categoryIcon;
        private BigDecimal remainingAmount;
        private int usagePercentage;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder spentAmount(BigDecimal spentAmount) {
            this.spentAmount = spentAmount;
            return this;
        }

        public Builder month(Integer month) {
            this.month = month;
            return this;
        }

        public Builder year(Integer year) {
            this.year = year;
            return this;
        }

        public Builder alertAtPercentage(Integer alertAtPercentage) {
            this.alertAtPercentage = alertAtPercentage;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder categoryIcon(String categoryIcon) {
            this.categoryIcon = categoryIcon;
            return this;
        }

        public Builder remainingAmount(BigDecimal remainingAmount) {
            this.remainingAmount = remainingAmount;
            return this;
        }

        public Builder usagePercentage(int usagePercentage) {
            this.usagePercentage = usagePercentage;
            return this;
        }
        public BudgetResponse build() {
            return new BudgetResponse(id, amount, spentAmount, month, year, alertAtPercentage, isActive, createdAt, categoryId, categoryName, categoryIcon, remainingAmount, usagePercentage);
        }
    }
}


