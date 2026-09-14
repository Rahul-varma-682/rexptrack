package com.rahul.rexptrack.service;

import com.rahul.rexptrack.exception.ResourceNotFoundException;
import com.rahul.rexptrack.exception.UnauthorizedException;
import com.rahul.rexptrack.model.Category;
import com.rahul.rexptrack.model.User;
import com.rahul.rexptrack.repository.CategoryRepository;
import com.rahul.rexptrack.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override @Transactional(readOnly = true)
    public List<Category> getAllForUser(Long userId) { return categoryRepository.findByUserIdOrIsDefaultTrue(userId); }

    @Override @Transactional
    public Category createCategory(String name, String icon, String color, String type, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        Category category = new Category();
        category.setName(name); category.setIcon(icon); category.setColor(color);
        category.setType(Category.CategoryType.valueOf(type.toUpperCase())); category.setUser(user); category.setDefault(false);
        return categoryRepository.save(category);
    }

    @Override @Transactional
    public void deleteCategory(Long id, Long userId) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", id));
        if (category.isDefault() || category.getUser() == null || !Objects.equals(category.getUser().getId(), userId)) {
            throw new UnauthorizedException("You cannot delete this category");
        }
        categoryRepository.delete(category);
    }

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
}

