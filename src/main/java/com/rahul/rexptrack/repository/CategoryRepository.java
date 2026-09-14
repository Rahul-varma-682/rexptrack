package com.rahul.rexptrack.repository;

import com.rahul.rexptrack.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserIdOrIsDefaultTrue(Long userId);
    List<Category> findByUserIdAndType(Long userId, Category.CategoryType type);
}
