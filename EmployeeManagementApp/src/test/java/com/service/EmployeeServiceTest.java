
package com.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dto.EmployeeDTO;
import com.entity.Employee;
import com.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @Test
    void testCreateEmployee() {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Roja");
        emp.setRole("Developer");
        emp.setSalary(80000);

        when(repository.save(any(Employee.class))).thenReturn(emp);

        EmployeeDTO dto = new EmployeeDTO(null, "Roja", "Developer", 80000);
        EmployeeDTO saved = service.createDto(dto);

        assertThat(saved.getName()).isEqualTo("Roja");
        verify(repository, times(1)).save(any(Employee.class));
    }
}