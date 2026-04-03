package com.example.employeecrud.dao;
import com.example.employeecrud.entity.Employee;
import java.util.List;
public interface EmployeeDao {
        void saveEmployee(Employee employee);
        Employee getEmployeeById(int id);
        List<Employee> getAllEmployees();
        Employee updateEmployee(Employee employee);
        void deleteEmployee(int id);
}
