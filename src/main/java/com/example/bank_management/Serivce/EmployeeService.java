package com.example.bank_management.Serivce;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bank_management.API.ApiException;
import com.example.bank_management.DTO.EmployeeUserDTO;
import com.example.bank_management.Model.Employee;
import com.example.bank_management.Model.User;
import com.example.bank_management.Repository.AuthRepository;
import com.example.bank_management.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository; 

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll(); 
    }

    public void registerEmployee(EmployeeUserDTO employeeUserDTO){
        String hash = new BCryptPasswordEncoder().encode(employeeUserDTO.getPassword()); 
        User user = new User();
        user.setUsername(employeeUserDTO.getUsername());
        user.setPassword(hash);
        user.setName(employeeUserDTO.getName());
        user.setEmail(employeeUserDTO.getEmail());
        user.setRole("EMPLOYEE");

        Employee employee = new Employee(); 
        employee.setId(null);
        employee.setPosition(employeeUserDTO.getPosition());
        employee.setSalary(employeeUserDTO.getSalary());

        user.setEmployee(employee);
        employee.setUser(user);

        authRepository.save(user); 
        employeeRepository.save(employee); 
    }

    public void updateEmployee(Integer authId, Integer employeeId, EmployeeUserDTO employeeUserDTO){
        User user = authRepository.findUserById(employeeId)
            .orElseThrow(() -> new ApiException("EMPLOYEE NOT FOUND!")); 

        user.setUsername(employeeUserDTO.getUsername());
        user.setPassword(employeeUserDTO.getPassword());
        user.setName(employeeUserDTO.getName());
        user.setEmail(employeeUserDTO.getEmail());
        user.getEmployee().setPosition(employeeUserDTO.getPosition());
        user.getEmployee().setSalary(employeeUserDTO.getSalary());

        authRepository.save(user); 
    }

    public void deleteEmployee(Integer employeeId){
        User user = authRepository.findUserById(employeeId)
            .orElseThrow(() -> new ApiException("EMPLOYEE NOT FOUND!")); 

        authRepository.delete(user);
    }
}
