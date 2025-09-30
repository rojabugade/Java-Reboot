package com.example.student_api.integration;

import com.example.student_api.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCompleteStudentLifecycle() {
        String baseUrl = "http://localhost:" + port + "/api/students";
        
        // Create a student
        Student newStudent = new Student(null, "Integration Test Student", "test@integration.com", "Software Engineering");
        
        ResponseEntity<Student> createResponse = restTemplate.postForEntity(baseUrl, newStudent, Student.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());
        Student createdStudent = createResponse.getBody();
        Long studentId = createdStudent.getId();

        // Get the created student
        ResponseEntity<Student> getResponse = restTemplate.getForEntity(baseUrl + "/" + studentId, Student.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Integration Test Student", getResponse.getBody().getName());

        // Update the student
        Student updatedStudent = new Student(studentId, "Updated Integration Student", "updated@integration.com", "Data Science");
        HttpEntity<Student> requestEntity = new HttpEntity<>(updatedStudent);
        ResponseEntity<Student> updateResponse = restTemplate.exchange(baseUrl + "/" + studentId, HttpMethod.PUT, requestEntity, Student.class);
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());

        // Get all students
        ResponseEntity<Student[]> getAllResponse = restTemplate.getForEntity(baseUrl, Student[].class);
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());

        // Delete the student
        restTemplate.delete(baseUrl + "/" + studentId);
    }

    @Test
    void testValidationErrors() {
        String baseUrl = "http://localhost:" + port + "/api/students";
        
        // Test with invalid student (missing required fields)
        Student invalidStudent = new Student(null, "", "invalid-email", "");
        
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, invalidStudent, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testSwaggerEndpoints() {
        // Test that API docs are accessible
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api-docs", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("openapi"));
        assertTrue(response.getBody().contains("info"));
    }

    @Test
    void testActuatorEndpoints() {
        // Test health endpoint
        ResponseEntity<String> healthResponse = restTemplate.getForEntity("http://localhost:" + port + "/actuator/health", String.class);
        assertEquals(HttpStatus.OK, healthResponse.getStatusCode());
        assertTrue(healthResponse.getBody().contains("UP"));

        // Test info endpoint
        ResponseEntity<String> infoResponse = restTemplate.getForEntity("http://localhost:" + port + "/actuator/info", String.class);
        assertEquals(HttpStatus.OK, infoResponse.getStatusCode());
    }
}