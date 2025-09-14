package com.example.todoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.todoapp.controller.TodoNotFoundException;
import com.example.todoapp.model.Todo;
import com.example.todoapp.model.TodoDTO;
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

    public TodoDTO toDto(Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setCompleted(todo.isCompleted());
        return dto;
    }

    public Todo getTodoById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTodoById'");
    }

    public Todo getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with ID " +
                        id));
    }
}