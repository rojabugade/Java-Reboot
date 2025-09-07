package com.example.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	@Autowired
	private TodoService service;

	@GetMapping
	public List<Todo> getTodos() {
		return service.getAll();
	}
	
	@PostMapping
	public Todo addTodo(@RequestBody Todo todo) {
		return service.save(todo);
	}

	@PutMapping("/{id}")
	public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
		todo.setId(id);
		return service.save(todo);
	}

	@DeleteMapping("/{id}")
	public void deleteTodo(@PathVariable Long id) {
		service.delete(id);
	}
}