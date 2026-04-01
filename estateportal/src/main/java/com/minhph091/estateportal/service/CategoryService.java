package com.minhph091.estateportal.service;

import com.minhph091.estateportal.dao.CategoryDao;
import com.minhph091.estateportal.dto.CategoryRequest;
import com.minhph091.estateportal.dto.CategoryResponse;
import com.minhph091.estateportal.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryDao.getCategories();
        List<CategoryResponse> categoryRequestList = new ArrayList<>();
        for (Category category : categories) {
            categoryRequestList.add(mapToResponse(category));
        }
        return categoryRequestList;
    }

    @Transactional
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        Category category = categoryDao.addCategory(mapToEntity(categoryRequest));
        return mapToResponse(category);
    }

    public Category mapToEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return category;
    }

    public CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getCategoryID());
        response.setName(category.getName());
        return response;
    }

    public CategoryRequest mapToDTO(Category category) {
        CategoryRequest categoryDao = new CategoryRequest();
        categoryDao.setName(category.getName());
        return categoryDao;
    }


}
