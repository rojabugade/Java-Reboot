package com.example.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.model.*;

public interface TodoRepository extends
        JpaRepository<Todo, Long> {
    List<Todo> findByTitleContainingIgnoreCase(String keyword);
}