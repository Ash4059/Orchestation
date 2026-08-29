package com.example.orchestation.Controller;

import com.example.orchestation.DTO.EmployeeInfoDto;
import com.example.orchestation.DTO.EmployeeResponseDto;
import com.example.orchestation.DTO.UpdateEmployeeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orchestation.DTO.CreateEmployeeRequestDto;
import com.example.orchestation.Services.EmployeeServices;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody CreateEmployeeRequestDto employeeDto) {
        EmployeeResponseDto employeeResponseDto = employeeServices.saveEmployee(employeeDto);
        return ResponseEntity.ok(employeeResponseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Long id) {
        EmployeeInfoDto employeeInfoDto = employeeServices.findEmployeeById(id);
        return ResponseEntity.ok(employeeInfoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeRequestDto updateEmployeeRequestDto) {
        EmployeeResponseDto employeeResponseDto = employeeServices.updateEmployee(id, updateEmployeeRequestDto);
        return ResponseEntity.ok(employeeResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        EmployeeResponseDto employeeResponseDto = employeeServices.deleteEmployee(id);
        return ResponseEntity.ok(employeeResponseDto);
    }

}
