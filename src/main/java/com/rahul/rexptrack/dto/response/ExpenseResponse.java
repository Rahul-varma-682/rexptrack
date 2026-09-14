package com.rahul.rexptrack.dto.response;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class ExpenseResponse {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String paymentMethod;
    private Boolean isRecurring;
    private String tags;
    private LocalDateTime createdAt;
    private Long categoryId;
    private String categoryName;
    private String categoryIcon;
    private String categoryColor;

    public ExpenseResponse() {
    }

    public ExpenseResponse(Long id, BigDecimal amount, String description, LocalDate date, String paymentMethod, Boolean isRecurring, String tags, LocalDateTime createdAt, Long categoryId, String categoryName, String categoryIcon, String categoryColor) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.isRecurring = isRecurring;
        this.tags = tags;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.categoryColor = categoryColor;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public Boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public String getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private BigDecimal amount;
        private String description;
        private LocalDate date;
        private String paymentMethod;
        private Boolean isRecurring;
        private String tags;
        private LocalDateTime createdAt;
        private Long categoryId;
        private String categoryName;
        private String categoryIcon;
        private String categoryColor;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder isRecurring(Boolean isRecurring) {
            this.isRecurring = isRecurring;
            return this;
        }

        public Builder tags(String tags) {
            this.tags = tags;
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

        public Builder categoryColor(String categoryColor) {
            this.categoryColor = categoryColor;
            return this;
        }
        public ExpenseResponse build() {
            return new ExpenseResponse(id, amount, description, date, paymentMethod, isRecurring, tags, createdAt, categoryId, categoryName, categoryIcon, categoryColor);
        }
    }
}


