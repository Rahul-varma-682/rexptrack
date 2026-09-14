package com.rahul.rexptrack.dto.response;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class IncomeResponse {
    private Long id;
    private BigDecimal amount;
    private String source;
    private String description;
    private LocalDate date;
    private Boolean isRecurring;
    private LocalDateTime createdAt;
    private Long categoryId;
    private String categoryName;
    private String categoryIcon;

    public IncomeResponse() {
    }

    public IncomeResponse(Long id, BigDecimal amount, String source, String description, LocalDate date, Boolean isRecurring, LocalDateTime createdAt, Long categoryId, String categoryName, String categoryIcon) {
        this.id = id;
        this.amount = amount;
        this.source = source;
        this.description = description;
        this.date = date;
        this.isRecurring = isRecurring;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private BigDecimal amount;
        private String source;
        private String description;
        private LocalDate date;
        private Boolean isRecurring;
        private LocalDateTime createdAt;
        private Long categoryId;
        private String categoryName;
        private String categoryIcon;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
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

        public Builder isRecurring(Boolean isRecurring) {
            this.isRecurring = isRecurring;
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
        public IncomeResponse build() {
            return new IncomeResponse(id, amount, source, description, date, isRecurring, createdAt, categoryId, categoryName, categoryIcon);
        }
    }
}


