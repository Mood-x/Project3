package com.example.bank_management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_management.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    Optional<Employee> findUserById(Integer id);
}
