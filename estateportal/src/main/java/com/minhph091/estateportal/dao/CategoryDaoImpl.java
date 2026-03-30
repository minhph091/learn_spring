package com.minhph091.estateportal.dao;

import com.minhph091.estateportal.entity.Category;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private final EntityManager entityManager;

    @Autowired
    public CategoryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addCategory(Category category) {
        entityManager.persist(category);
    }

    @Override
    public List<Category> getCategories() {
        return entityManager.createQuery("SELECT e FROM Category e ", Category.class).getResultList();
    }

    @Override
    public Category findCategoryById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public void updateCategory(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void deleteCategory(int id) {
        Category category = entityManager.find(Category.class, id);
        if (category != null) {
            entityManager.remove(category);
        }
    }
}
