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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo Management", description = "APIs for managing todo items")
public class TodoController {
	@Autowired
	private TodoService service;

	@GetMapping
	@Operation(summary = "Get all todos", description = "Retrieve a list of all todo items")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved todos")
	public List<Todo> getTodos() {
		return service.getAll();
	}

	@PostMapping
	@Operation(summary = "Create a new todo", description = "Add a new todo item to the list")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Todo created successfully"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
	})
	public Todo addTodo(@RequestBody @Parameter(description = "Todo item to be created") Todo todo) {
		return service.save(todo);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a todo", description = "Update an existing todo item by ID")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Todo updated successfully"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Todo not found")
	})
	public Todo updateTodo(
			@PathVariable @Parameter(description = "ID of the todo to update") Long id, 
			@RequestBody @Parameter(description = "Updated todo data") Todo todo) {
		todo.setId(id);
		return service.save(todo);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a todo", description = "Remove a todo item by ID")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Todo deleted successfully"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Todo not found")
	})
	public void deleteTodo(@PathVariable @Parameter(description = "ID of the todo to delete") Long id) {
		service.delete(id);
	}

	@GetMapping("/page")
	@Operation(summary = "Get todos with pagination", description = "Retrieve todos with pagination support")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved paginated todos")
	})
	public Page<Todo> getTodosWithPagination(
			@RequestParam(defaultValue = "0") @Parameter(description = "Page number (0-indexed)") int page,
			@RequestParam(defaultValue = "5") @Parameter(description = "Number of items per page") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return service.getTodosPaginated(pageable);
	}

	@GetMapping("/search")
	@Operation(summary = "Search todos", description = "Search for todos by keyword")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
	public List<Todo> searchTodos(
			@RequestParam @Parameter(description = "Keyword to search for in todo titles") String keyword) {
		return service.searchTodos(keyword);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get todo by ID", description = "Retrieve a specific todo item by its ID")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Todo found successfully", 
			content = @Content(schema = @Schema(implementation = com.example.todoapp.model.ApiResponse.class))),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Todo not found")
	})
	public ResponseEntity<ApiResponse<TodoDTO>> getTodo(
			@PathVariable @Parameter(description = "ID of the todo to retrieve") Long id) {
		Todo todo = service.getTodoById(id);
		TodoDTO dto = service.toDto(todo);

		ApiResponse<TodoDTO> response = new ApiResponse<>("success", "Todo fetched", dto);
		return ResponseEntity.ok(response);
	}
}