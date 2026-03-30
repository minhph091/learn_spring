package com.example.employeecrud.service;

import com.example.employeecrud.dao.EmployeeDao;
import com.example.employeecrud.dto.EmployeeCreateRequest;
import com.example.employeecrud.dto.EmployeeResponse;
import com.example.employeecrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;

    @Autowired
    // Constructor này là tùy chọn nếu chỉ có một consturtor, Spring sẽ tự động sử dụng nó để inject dependency
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional
    public EmployeeResponse saveEmployee(EmployeeCreateRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employeeDao.saveEmployee(employee);
        return mapToEmployeeResponse(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(int id) {
        return mapToEmployeeResponse(employeeDao.getEmployeeById(id));
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Transactional
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    public EmployeeResponse mapToEmployeeResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFullName(employee.getFullName());
        response.setEmail(employee.getEmail());
        return response;
    }
}
