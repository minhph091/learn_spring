package com.minhph091.demo.api;

import com.minhph091.demo.dao.Student;
import com.minhph091.demo.dto.StudentErrorResponse;
import com.minhph091.demo.dto.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoRestController {
    List<Student> students;
    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        students.add(new Student(1,"Minh",25));
        students.add(new Student(2,"My",21));

    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Minh",25));
        students.add(new Student(2,"My",21));
        return students;
    }
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if(studentId < 1 || studentId > students.size())
            throw new StudentNotFoundException("Student not found: " + studentId);
        return students.get(studentId-1);
    }

}
