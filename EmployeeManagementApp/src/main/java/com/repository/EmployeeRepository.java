package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Employee;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByRoleIgnoreCase(String role);

}
