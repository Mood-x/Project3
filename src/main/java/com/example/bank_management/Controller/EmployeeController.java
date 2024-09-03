package com.example.bank_management.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_management.API.ApiRespinse;
import com.example.bank_management.DTO.EmployeeUserDTO;
import com.example.bank_management.Model.Employee;
import com.example.bank_management.Model.User;
import com.example.bank_management.Serivce.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService; 

    @GetMapping("/get-all-employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees()); 
    }

    @PostMapping("/register-employee")
    public ResponseEntity<ApiRespinse> registerEmployee(@Valid @RequestBody EmployeeUserDTO employeeUserDTO){
        employeeService.registerEmployee(employeeUserDTO);
        return ResponseEntity.ok(new ApiRespinse("Employee added successfully")); 
    }

    @PutMapping("/update-employee")
    public ResponseEntity<ApiRespinse> updateEmployee(@AuthenticationPrincipal User auth, @RequestParam Integer employeeId, @Valid @RequestBody EmployeeUserDTO employeeUserDTO){
        employeeService.updateEmployee(auth.getId(), employeeId, employeeUserDTO);
        return ResponseEntity.ok(new ApiRespinse("Employee updated successfully")); 
    }

    @DeleteMapping("/delete-employee")
    public ResponseEntity<ApiRespinse> registerEmployee(@RequestParam Integer employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(new ApiRespinse("Employee delete successfully")); 
    }
}
