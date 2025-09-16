package com.dto;

import jakarta.validation.constraints.NotBlank;

public class EmployeeDTO {
	private Long id;
	@NotBlank(message = "Name is required")
	private String name;
	private String role;
	private double salary;

	public EmployeeDTO() {
	}

	public EmployeeDTO(Long id, String name, String role, double salary) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
