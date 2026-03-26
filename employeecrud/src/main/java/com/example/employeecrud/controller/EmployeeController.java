package com.example.employeecrud.controller;

import com.example.employeecrud.dto.EmployeeCreateRequest;
import com.example.employeecrud.dto.EmployeeResponse;
import com.example.employeecrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.employeecrud.service.EmployeeService;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeCreateRequest request) {

        EmployeeResponse employeeResponse = employeeService.saveEmployee(request);
        return ResponseEntity.ok(employeeResponse);
    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

}
