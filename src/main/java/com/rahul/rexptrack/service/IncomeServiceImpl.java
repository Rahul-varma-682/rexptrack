package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.IncomeRequest;
import com.rahul.rexptrack.dto.response.IncomeResponse;
import com.rahul.rexptrack.exception.ResourceNotFoundException;
import com.rahul.rexptrack.exception.UnauthorizedException;
import com.rahul.rexptrack.model.Category;
import com.rahul.rexptrack.model.Income;
import com.rahul.rexptrack.model.User;
import com.rahul.rexptrack.repository.CategoryRepository;
import com.rahul.rexptrack.repository.IncomeRepository;
import com.rahul.rexptrack.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override @Transactional(readOnly = true)
    public List<IncomeResponse> getAllIncomes(Long userId) { return incomeRepository.findByUserIdOrderByDateDesc(userId).stream().map(this::toResponse).toList(); }

    @Override @Transactional(readOnly = true)
    public IncomeResponse getIncomeById(Long id, Long userId) { return toResponse(ownedIncome(id, userId)); }

    @Override @Transactional
    public IncomeResponse createIncome(IncomeRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", request.getCategoryId()));
        Income income = new Income();
        income.setUser(user); income.setCategory(category); income.setAmount(request.getAmount()); income.setSource(request.getSource());
        income.setDescription(request.getDescription()); income.setDate(request.getDate()); income.setRecurring(Boolean.TRUE.equals(request.getIsRecurring()));
        return toResponse(incomeRepository.save(income));
    }

    @Override @Transactional
    public IncomeResponse updateIncome(Long id, IncomeRequest request, Long userId) {
        Income income = ownedIncome(id, userId);
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", request.getCategoryId()));
        income.setCategory(category); income.setAmount(request.getAmount()); income.setSource(request.getSource());
        income.setDescription(request.getDescription()); income.setDate(request.getDate()); income.setRecurring(Boolean.TRUE.equals(request.getIsRecurring()));
        return toResponse(incomeRepository.save(income));
    }

    @Override @Transactional
    public void deleteIncome(Long id, Long userId) { incomeRepository.delete(ownedIncome(id, userId)); }

    @Override @Transactional(readOnly = true)
    public BigDecimal getTotalForMonth(Long userId, int month, int year) {
        BigDecimal total = incomeRepository.sumByUserAndMonth(userId, month, year);
        return total == null ? BigDecimal.ZERO : total;
    }

    private Income ownedIncome(Long id, Long userId) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Income", id));
        if (!Objects.equals(income.getUser().getId(), userId)) throw new UnauthorizedException("You do not own this income");
        return income;
    }

    private IncomeResponse toResponse(Income income) {
        Category category = income.getCategory();
        IncomeResponse response = new IncomeResponse();
        response.setId(income.getId()); response.setAmount(income.getAmount()); response.setSource(income.getSource());
        response.setDescription(income.getDescription()); response.setDate(income.getDate()); response.setIsRecurring(income.isRecurring());
        response.setCreatedAt(income.getCreatedAt()); response.setCategoryId(category.getId()); response.setCategoryName(category.getName());
        response.setCategoryIcon(category.getIcon());
        return response;
    }

    public IncomeServiceImpl(IncomeRepository incomeRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
}

