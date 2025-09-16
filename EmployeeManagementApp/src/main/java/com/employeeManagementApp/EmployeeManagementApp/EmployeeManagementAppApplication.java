package com.employeeManagementApp.EmployeeManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com"})
@EnableJpaRepositories(basePackages = "com.repository")
@EntityScan(basePackages = "com.entity")
public class EmployeeManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementAppApplication.class, args);
	}

}
