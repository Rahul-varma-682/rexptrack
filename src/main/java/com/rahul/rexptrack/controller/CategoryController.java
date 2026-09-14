package com.rahul.rexptrack.controller;

import com.rahul.rexptrack.dto.response.ApiResponse;
import com.rahul.rexptrack.model.Category;
import com.rahul.rexptrack.service.CategoryService;
import com.rahul.rexptrack.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final SecurityUtil securityUtil;

    @GetMapping public ResponseEntity<ApiResponse<List<Category>>> getAll() { return ResponseEntity.ok(ApiResponse.success("Categories retrieved", categoryService.getAllForUser(securityUtil.getCurrentUserId()))); }
    @PostMapping public ResponseEntity<ApiResponse<Category>> create(@RequestParam String name, @RequestParam(required = false) String icon, @RequestParam(required = false) String color, @RequestParam(defaultValue = "EXPENSE") String type) { return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Category created", categoryService.createCategory(name, icon, color, type, securityUtil.getCurrentUserId()))); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { categoryService.deleteCategory(id, securityUtil.getCurrentUserId()); return ResponseEntity.noContent().build(); }

    public CategoryController(CategoryService categoryService, SecurityUtil securityUtil) {
        this.categoryService = categoryService;
        this.securityUtil = securityUtil;
    }
}


