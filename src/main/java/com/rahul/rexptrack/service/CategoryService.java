package com.rahul.rexptrack.service;

import com.rahul.rexptrack.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllForUser(Long userId);
    Category createCategory(String name, String icon, String color, String type, Long userId);
    void deleteCategory(Long id, Long userId);
}
