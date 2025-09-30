package com.example.student_api.controller;

import com.example.student_api.model.Student;
import com.example.student_api.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student testStudent;

    @BeforeEach
    void setUp() {
        testStudent = new Student(1L, "John Doe", "john.doe@example.com", "Computer Science");
    }

    @Test
    void testCreateStudent() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(testStudent);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.course").value("Computer Science"));
    }

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(
                testStudent,
                new Student(2L, "Jane Smith", "jane.smith@example.com", "Mathematics")
        );
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(testStudent);

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        Student updatedStudent = new Student(1L, "John Updated", "john.updated@example.com", "Data Science");
        when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.course").value("Data Science"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        when(studentService.deleteStudent(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted"));
    }

    @Test
    void testDeleteStudentNotFound() throws Exception {
        when(studentService.deleteStudent(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student not found"));
    }
}