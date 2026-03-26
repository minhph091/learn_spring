package com.example.employeecrud.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.employeecrud.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeImpl implements EmployeeDao {
    private final EntityManager entityManager;

    @Autowired
    public EmployeeImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return entityManager.
                createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }
}
