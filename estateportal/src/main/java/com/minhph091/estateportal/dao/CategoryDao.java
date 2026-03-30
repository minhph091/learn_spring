package com.minhph091.estateportal.dao;

import com.minhph091.estateportal.entity.Category;

import java.util.List;

public interface CategoryDao {
    void addCategory(Category category);

    List<Category> getCategories();

    Category findCategoryById(int id);

    void updateCategory(Category category);

    void deleteCategory(int id);
}
