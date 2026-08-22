package com.example.orchestation.Services;

import org.springframework.stereotype.Service;

import com.example.orchestation.Entity.Employee;
import com.example.orchestation.Repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServices {

    private final EmployeeRepository employeeRepository;

    public EmployeeServices(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.saveEmployee(employee);
    }

    @Transactional
    public void updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = findEmployeeById(id);
        if (existingEmployee != null) {
            employeeRepository.updateEmployee(employee);
        }
        else {
            // Handle the case where the employee with the given ID does not exist
            throw new IllegalArgumentException("Employee with ID " + id + " not found.");
        }
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employeeRepository.deleteEmployee(employee);
        } else {
            throw new IllegalArgumentException("Employee with ID " + id + " not found.");
        }
    }

}
