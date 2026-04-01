package com.minhph091.estateportal.controller;

import com.minhph091.estateportal.dto.CategoryRequest;
import com.minhph091.estateportal.dto.CategoryResponse;
import com.minhph091.estateportal.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{category_id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable(name = "category_id") int id) {
        CategoryResponse response = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody @Valid CategoryRequest request) {
        CategoryResponse response = categoryService.addCategory(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
