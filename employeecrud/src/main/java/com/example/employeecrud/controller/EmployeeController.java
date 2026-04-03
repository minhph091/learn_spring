package com.example.employeecrud.controller;

import com.example.employeecrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.employeecrud.service.EmployeeService;
import tools.jackson.databind.json.JsonMapper;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final JsonMapper jsonMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id).orElseThrow(
                () -> new RuntimeException("Không tìm thấy ID"));
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee request) {

        Employee employeeResponse = employeeService.saveEmployee(request);
        return ResponseEntity.ok(employeeResponse);
    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @PatchMapping("/employees/{id}")
    public Employee patchEmployee(@PathVariable int id, @RequestBody Map<String, Object> patchPayload) {
        Employee employee = employeeService.getEmployeeById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy Id"));
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Không chấp nhận id ở đây");
        }
        Employee patchEmployee = jsonMapper.updateValue(employee, patchPayload);
        return employeeService.saveEmployee(patchEmployee);
    }
}
