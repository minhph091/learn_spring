package com.minhph091.estateportal.service;

import com.minhph091.estateportal.dao.CategoryDao;
import com.minhph091.estateportal.dto.CategoryDTO;
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
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryDao.getCategories();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOList.add(mapToDTO(category));
        }
        return categoryDTOList;
    }

    public CategoryDTO mapToDTO(Category category) {
        CategoryDTO categoryDao = new CategoryDTO();
        categoryDao.setId(category.getCategoryID());
        categoryDao.setName(category.getName());
        return categoryDao;
    }


}
