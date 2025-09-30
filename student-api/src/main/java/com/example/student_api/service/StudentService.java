package com.example.student_api.service;

import com.example.student_api.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final Map<Long, Student> studentMap = new HashMap<>();
    private long currentId = 1;

    public Student createStudent(Student student) {
        student.setId(currentId++);
        studentMap.put(student.getId(), student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    public Student getStudentById(Long id) {
        return studentMap.get(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        updatedStudent.setId(id);
        studentMap.put(id, updatedStudent);
        return updatedStudent;
    }

    public boolean deleteStudent(Long id) {
        return studentMap.remove(id) != null;
    }
}
