package com.example.employeecrud.service;

import com.example.employeecrud.dao.EmployeeDao;
import com.example.employeecrud.dto.EmployeeResponse;
import com.example.employeecrud.entity.Employee;
import com.example.employeecrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;
    private final EmployeeRepository employeeRepository;

    @Autowired
    // Constructor này là tùy chọn nếu chỉ có một consturtor, Spring sẽ tự động sử dụng nó để inject dependency
    public EmployeeService(EmployeeDao employeeDao, EmployeeRepository employeeRepository) {
        this.employeeDao = employeeDao;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee saveEmployee(Employee request) {
        return employeeRepository.save(request);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeResponse mapToEmployeeResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFullName(employee.getFullName());
        response.setEmail(employee.getEmail());
        return response;
    }
}
