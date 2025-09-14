package com.example.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.model.ApiResponse;
import com.example.todoapp.model.Todo;
import com.example.todoapp.model.TodoDTO;
import com.example.todoapp.service.TodoService;

import jakarta.validation.Valid;

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

	@GetMapping("/page")
	public Page<Todo> getTodosWithPagination(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return service.getTodosPaginated(pageable);
	}

	@GetMapping("/search")
	public List<Todo> searchTodos(@RequestParam String keyword) {
		return service.searchTodos(keyword);
	}

	@PostMapping
	public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
		Todo saved = service.save(todo);
		return ResponseEntity.ok(saved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<TodoDTO>> getTodo(@PathVariable Long id) {
		Todo todo = service.getTodoById(id);
		TodoDTO dto = service.toDto(todo);

		ApiResponse<TodoDTO> response = new ApiResponse<>("success", "Todo fetched", dto);
		return ResponseEntity.ok(response);
	}
}