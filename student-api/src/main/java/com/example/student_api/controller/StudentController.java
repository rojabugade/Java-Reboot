package com.example.student_api.controller;

import com.example.student_api.model.Student;
import com.example.student_api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Create a new student", description = "Creates a new student record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    @Operation(summary = "Get all students", description = "Retrieves a list of all students")
    @ApiResponse(responseCode = "200", description = "List of students retrieved successfully")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID", description = "Retrieves a student by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student found"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public Student getStudentById(@Parameter(description = "Student ID") @PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student", description = "Updates an existing student record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public Student updateStudent(@Parameter(description = "Student ID") @PathVariable Long id, @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student", description = "Deletes a student record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public String deleteStudent(@Parameter(description = "Student ID") @PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        return deleted ? "Student deleted" : "Student not found";
    }
}
