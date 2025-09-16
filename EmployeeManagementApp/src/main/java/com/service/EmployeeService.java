package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.EmployeeDTO;
import com.entity.Employee;
import com.repository.EmployeeRepository;
import com.exception.ResourceNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repo;

	public EmployeeDTO createDto(EmployeeDTO dto) {
		Employee entity = toEntity(dto);
		Employee saved = repo.save(entity);
		return toDto(saved);
	}

	public List<EmployeeDTO> getAllList() {
		return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public EmployeeDTO getById(Long id) {
		Employee emp = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
		return toDto(emp);
	}

	public List<EmployeeDTO> getByRole(String role) {
		return repo.findByRoleIgnoreCase(role).stream().map(this::toDto).collect(Collectors.toList());
	}

	private EmployeeDTO toDto(Employee e) {
		if (e == null) return null;
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setRole(e.getRole());
		dto.setSalary(e.getSalary());
		return dto;
	}

	private Employee toEntity(EmployeeDTO dto) {
		if (dto == null) return null;
		Employee e = new Employee();
		e.setId(dto.getId());
		e.setName(dto.getName());
		e.setRole(dto.getRole());
		e.setSalary(dto.getSalary());
		return e;
	}
}
