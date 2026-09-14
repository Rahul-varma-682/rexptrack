package com.rahul.rexptrack.repository;

import com.rahul.rexptrack.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserIdAndStatus(Long userId, Goal.GoalStatus status);
    List<Goal> findByUserIdOrderByCreatedAtDesc(Long userId);
}
