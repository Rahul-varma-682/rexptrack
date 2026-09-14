package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.GoalRequest;
import com.rahul.rexptrack.dto.response.GoalResponse;
import com.rahul.rexptrack.exception.ResourceNotFoundException;
import com.rahul.rexptrack.exception.UnauthorizedException;
import com.rahul.rexptrack.model.Goal;
import com.rahul.rexptrack.model.User;
import com.rahul.rexptrack.repository.GoalRepository;
import com.rahul.rexptrack.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Override @Transactional(readOnly = true)
    public List<GoalResponse> getAllGoals(Long userId) { return goalRepository.findByUserIdOrderByCreatedAtDesc(userId).stream().map(this::toResponse).toList(); }

    @Override @Transactional
    public GoalResponse createGoal(GoalRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        Goal goal = new Goal();
        goal.setUser(user); goal.setTitle(request.getTitle()); goal.setDescription(request.getDescription()); goal.setTargetAmount(request.getTargetAmount());
        goal.setCurrentAmount(request.getCurrentAmount()); goal.setDeadline(request.getDeadline()); goal.setIcon(request.getIcon());
        goal.setPriority(Goal.GoalPriority.valueOf(request.getPriority().toUpperCase()));
        if (goal.getCurrentAmount().compareTo(goal.getTargetAmount()) >= 0) goal.setStatus(Goal.GoalStatus.COMPLETED);
        return toResponse(goalRepository.save(goal));
    }

    @Override @Transactional
    public GoalResponse contributeToGoal(Long id, BigDecimal amount, Long userId) {
        if (amount == null || amount.signum() <= 0) throw new IllegalArgumentException("Contribution amount must be positive");
        Goal goal = ownedGoal(id, userId);
        goal.setCurrentAmount(goal.getCurrentAmount().add(amount));
        if (goal.getCurrentAmount().compareTo(goal.getTargetAmount()) >= 0) goal.setStatus(Goal.GoalStatus.COMPLETED);
        return toResponse(goalRepository.save(goal));
    }

    @Override @Transactional
    public void deleteGoal(Long id, Long userId) { goalRepository.delete(ownedGoal(id, userId)); }

    private Goal ownedGoal(Long id, Long userId) {
        Goal goal = goalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Goal", id));
        if (!Objects.equals(goal.getUser().getId(), userId)) throw new UnauthorizedException("You do not own this goal");
        return goal;
    }

    private GoalResponse toResponse(Goal goal) {
        BigDecimal current = goal.getCurrentAmount() == null ? BigDecimal.ZERO : goal.getCurrentAmount();
        BigDecimal remaining = goal.getTargetAmount().subtract(current);
        int progress = goal.getTargetAmount().signum() == 0 ? 0 : current.multiply(BigDecimal.valueOf(100)).divide(goal.getTargetAmount(), 0, RoundingMode.HALF_UP).intValue();
        GoalResponse response = new GoalResponse();
        response.setId(goal.getId()); response.setTitle(goal.getTitle()); response.setDescription(goal.getDescription()); response.setTargetAmount(goal.getTargetAmount());
        response.setCurrentAmount(current); response.setDeadline(goal.getDeadline()); response.setIcon(goal.getIcon()); response.setStatus(goal.getStatus().name());
        response.setPriority(goal.getPriority().name()); response.setCreatedAt(goal.getCreatedAt()); response.setUpdatedAt(goal.getUpdatedAt());
        response.setRemainingAmount(remaining); response.setProgressPercentage(progress);
        return response;
    }

    public GoalServiceImpl(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }
}

