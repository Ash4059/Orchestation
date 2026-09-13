package com.example.orchestation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orchestation.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
