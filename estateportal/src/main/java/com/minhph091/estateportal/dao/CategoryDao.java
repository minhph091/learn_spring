package com.minhph091.estateportal.dao;

import com.minhph091.estateportal.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category addCategory(Category category);

    List<Category> getCategories();

    Category findCategoryById(int id);

    Category updateCategory(Category category);

    void deleteCategory(Category category);

    boolean existCategory(String name);
}
