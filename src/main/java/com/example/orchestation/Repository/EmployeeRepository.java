package com.example.orchestation.Repository;

import org.springframework.stereotype.Repository;

import com.example.orchestation.Entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    public void deleteEmployee(Employee employee) {
        entityManager.remove(employee);
    }

}
