package com.example.todoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repo;

    public List<Todo> getAll() {
        return repo.findAll();
    }

    public Todo save(Todo todo) {
        return repo.save(todo);
    }

    /*
     * public TodoRepository save(TodoRepository todo) {
     * return repo.save(todo);
     * }
     */

    public Page<Todo> getTodosPaginated(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Todo> searchTodos(String keyword) {
        return repo.findByTitleContainingIgnoreCase(keyword);
    }
}