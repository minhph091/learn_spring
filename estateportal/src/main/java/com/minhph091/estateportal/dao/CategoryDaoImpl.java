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
    public Category addCategory(Category category) {
        entityManager.persist(category);
        return category;
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
    public Category updateCategory(Category category) {
        return entityManager.merge(category);
    }

    @Override
    public void deleteCategory(Category category) {
        entityManager.remove(category);
    }

    @Override
    public boolean existCategory(String name) {
        Category category = entityManager.createQuery("SELECT e FROM Category e where e.name = :name", Category.class).
                setParameter("name", name).getSingleResult();
        return category != null;
    }
}
