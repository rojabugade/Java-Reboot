package com.example.student_api.integration;

import com.example.student_api.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
public class StudentApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCompleteStudentLifecycle() throws Exception {
        // Create a student
        Student newStudent = new Student(null, "Integration Test Student", "test@integration.com", "Software Engineering");
        
        String createdStudentJson = mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Test Student"))
                .andReturn().getResponse().getContentAsString();

        Student createdStudent = objectMapper.readValue(createdStudentJson, Student.class);
        Long studentId = createdStudent.getId();

        // Get the created student
        mockMvc.perform(get("/api/students/" + studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Test Student"));

        // Update the student
        Student updatedStudent = new Student(studentId, "Updated Integration Student", "updated@integration.com", "Data Science");
        mockMvc.perform(put("/api/students/" + studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Integration Student"));

        // Get all students (should include our student)
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        // Delete the student
        mockMvc.perform(delete("/api/students/" + studentId))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted"));
    }

    @Test
    void testValidationErrors() throws Exception {
        // Test with invalid student (missing required fields)
        Student invalidStudent = new Student(null, "", "invalid-email", "");
        
        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidStudent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSwaggerEndpoints() throws Exception {
        // Test that Swagger UI is accessible
        mockMvc.perform(get("/swagger-ui.html"))
                .andExpect(status().is3xxRedirection());

        // Test that API docs are accessible
        mockMvc.perform(get("/api-docs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testActuatorEndpoints() throws Exception {
        // Test health endpoint
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));

        // Test info endpoint
        mockMvc.perform(get("/actuator/info"))
                .andExpect(status().isOk());
    }
}