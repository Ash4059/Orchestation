package com.example.orchestation.Services;

import com.example.orchestation.DTO.CreateEmployeeRequestDto;
import com.example.orchestation.DTO.EmployeeInfoDto;
import com.example.orchestation.DTO.EmployeeResponseDto;
import com.example.orchestation.DTO.UpdateEmployeeRequestDto;
import com.example.orchestation.Mapper.EmployeeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.Employee;
import com.example.orchestation.Repository.EmployeeRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServices {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private Employee getEmployeeById(Long Id) {
        return employeeRepository.findEmployeeById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + Id));
    }

    public EmployeeInfoDto findEmployeeById(Long id) {
        Employee employee = getEmployeeById(id);
        return employeeMapper.toDto(employee);
    }

    @Transactional
    public EmployeeResponseDto saveEmployee(CreateEmployeeRequestDto employeeDto) {
        Employee saveEmployee = employeeMapper.toEntity(employeeDto);
        employeeRepository.saveEmployee(saveEmployee);
        String message = String.format("Employee with user ID: %s, created successfully!!!", saveEmployee.getId());
        return employeeMapper.toResponseDto(saveEmployee, message);

    }

    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, UpdateEmployeeRequestDto employeeRequestDto) {
        Employee employee = getEmployeeById(id);
        employeeMapper.updateEntityFromDto(employeeRequestDto, employee);

        //Commit triggers automatic SQL update for changed columns
        String message = String.format("Employee with ID %d updated successfully", id);
        return employeeMapper.toResponseDto(employee, message);
    }

    @Transactional
    public EmployeeResponseDto deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.deleteEmployee(employee);
        //Commit triggers automatic SQL update for changed columns
        String message = String.format("Employee with ID %d deleted successfully", id);
        return employeeMapper.toResponseDto(employee,message);
    }

}
