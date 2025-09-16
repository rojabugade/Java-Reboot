package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EmployeeDTO;
import com.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@PostMapping
	public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO dto) {
		return ResponseEntity.ok(service.createDto(dto));
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAll() {
		return ResponseEntity.ok(service.getAllList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@GetMapping("/role/{role}")
	public ResponseEntity<List<EmployeeDTO>> getByRole(@PathVariable String role) {
		return ResponseEntity.ok(service.getByRole(role));
	}
}