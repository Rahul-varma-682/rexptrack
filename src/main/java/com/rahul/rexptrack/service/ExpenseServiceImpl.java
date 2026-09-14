package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.ExpenseRequest;
import com.rahul.rexptrack.dto.response.ExpenseResponse;
import com.rahul.rexptrack.exception.ResourceNotFoundException;
import com.rahul.rexptrack.exception.UnauthorizedException;
import com.rahul.rexptrack.model.Category;
import com.rahul.rexptrack.model.Expense;
import com.rahul.rexptrack.model.User;
import com.rahul.rexptrack.repository.CategoryRepository;
import com.rahul.rexptrack.repository.ExpenseRepository;
import com.rahul.rexptrack.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override @Transactional(readOnly = true)
    public List<ExpenseResponse> getAllExpenses(Long userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId).stream().map(this::toResponse).toList();
    }

    @Override @Transactional(readOnly = true)
    public ExpenseResponse getExpenseById(Long id, Long userId) { return toResponse(ownedExpense(id, userId)); }

    @Override @Transactional
    public ExpenseResponse createExpense(ExpenseRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", request.getCategoryId()));
        Expense expense = new Expense();
        expense.setUser(user); expense.setCategory(category); expense.setAmount(request.getAmount()); expense.setDescription(request.getDescription());
        expense.setDate(request.getDate()); expense.setPaymentMethod(Expense.PaymentMethod.valueOf(request.getPaymentMethod().toUpperCase()));
        expense.setRecurring(Boolean.TRUE.equals(request.getIsRecurring())); expense.setTags(request.getTags());
        return toResponse(expenseRepository.save(expense));
    }

    @Override @Transactional
    public ExpenseResponse updateExpense(Long id, ExpenseRequest request, Long userId) {
        Expense expense = ownedExpense(id, userId);
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", request.getCategoryId()));
        expense.setCategory(category); expense.setAmount(request.getAmount()); expense.setDescription(request.getDescription());
        expense.setDate(request.getDate()); expense.setPaymentMethod(Expense.PaymentMethod.valueOf(request.getPaymentMethod().toUpperCase()));
        expense.setRecurring(Boolean.TRUE.equals(request.getIsRecurring())); expense.setTags(request.getTags());
        return toResponse(expenseRepository.save(expense));
    }

    @Override @Transactional
    public void deleteExpense(Long id, Long userId) { expenseRepository.delete(ownedExpense(id, userId)); }

    @Override @Transactional(readOnly = true)
    public BigDecimal getTotalForMonth(Long userId, int month, int year) {
        BigDecimal total = expenseRepository.sumByUserAndMonth(userId, month, year);
        return total == null ? BigDecimal.ZERO : total;
    }

    private Expense ownedExpense(Long id, Long userId) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense", id));
        if (!Objects.equals(expense.getUser().getId(), userId)) throw new UnauthorizedException("You do not own this expense");
        return expense;
    }

    private ExpenseResponse toResponse(Expense expense) {
        Category category = expense.getCategory();
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getId()); response.setAmount(expense.getAmount()); response.setDescription(expense.getDescription());
        response.setDate(expense.getDate()); response.setPaymentMethod(expense.getPaymentMethod().name()); response.setIsRecurring(expense.isRecurring());
        response.setTags(expense.getTags()); response.setCreatedAt(expense.getCreatedAt()); response.setCategoryId(category.getId());
        response.setCategoryName(category.getName()); response.setCategoryIcon(category.getIcon()); response.setCategoryColor(category.getColor());
        return response;
    }

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
}

