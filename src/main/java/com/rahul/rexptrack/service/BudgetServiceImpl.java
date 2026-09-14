package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.BudgetRequest;
import com.rahul.rexptrack.dto.response.BudgetResponse;
import com.rahul.rexptrack.exception.ResourceNotFoundException;
import com.rahul.rexptrack.exception.UnauthorizedException;
import com.rahul.rexptrack.model.Budget;
import com.rahul.rexptrack.model.Category;
import com.rahul.rexptrack.model.User;
import com.rahul.rexptrack.repository.BudgetRepository;
import com.rahul.rexptrack.repository.CategoryRepository;
import com.rahul.rexptrack.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override @Transactional(readOnly = true)
    public List<BudgetResponse> getCurrentMonthBudgets(Long userId) {
        LocalDate now = LocalDate.now();
        return budgetRepository.findByUserIdAndMonthAndYear(userId, now.getMonthValue(), now.getYear()).stream().map(this::toResponse).toList();
    }

    @Override @Transactional
    public BudgetResponse createBudget(BudgetRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", request.getCategoryId()));
        Budget budget = new Budget();
        budget.setUser(user); budget.setCategory(category); budget.setAmount(request.getAmount());
        budget.setMonth(request.getMonth()); budget.setYear(request.getYear()); budget.setAlertAtPercentage(request.getAlertAtPercentage());
        return toResponse(budgetRepository.save(budget));
    }

    @Override @Transactional
    public BudgetResponse updateBudget(Long id, BudgetRequest request, Long userId) {
        Budget budget = ownedBudget(id, userId);
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", request.getCategoryId()));
        budget.setCategory(category); budget.setAmount(request.getAmount()); budget.setMonth(request.getMonth()); budget.setYear(request.getYear());
        budget.setAlertAtPercentage(request.getAlertAtPercentage());
        return toResponse(budgetRepository.save(budget));
    }

    @Override @Transactional
    public void deleteBudget(Long id, Long userId) { budgetRepository.delete(ownedBudget(id, userId)); }

    private Budget ownedBudget(Long id, Long userId) {
        Budget budget = budgetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Budget", id));
        if (!Objects.equals(budget.getUser().getId(), userId)) throw new UnauthorizedException("You do not own this budget");
        return budget;
    }

    private BudgetResponse toResponse(Budget budget) {
        BigDecimal spent = budget.getSpentAmount() == null ? BigDecimal.ZERO : budget.getSpentAmount();
        BigDecimal remaining = budget.getAmount().subtract(spent);
        int usage = budget.getAmount().signum() == 0 ? 0 : spent.multiply(BigDecimal.valueOf(100)).divide(budget.getAmount(), 0, RoundingMode.HALF_UP).intValue();
        BudgetResponse response = new BudgetResponse();
        response.setId(budget.getId()); response.setAmount(budget.getAmount()); response.setSpentAmount(spent); response.setMonth(budget.getMonth());
        response.setYear(budget.getYear()); response.setAlertAtPercentage(budget.getAlertAtPercentage()); response.setIsActive(budget.isActive());
        response.setCreatedAt(budget.getCreatedAt()); response.setCategoryId(budget.getCategory().getId());
        response.setCategoryName(budget.getCategory().getName()); response.setCategoryIcon(budget.getCategory().getIcon());
        response.setRemainingAmount(remaining); response.setUsagePercentage(usage);
        return response;
    }

    public BudgetServiceImpl(BudgetRepository budgetRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
}

