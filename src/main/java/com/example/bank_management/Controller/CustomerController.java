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
import com.example.bank_management.DTO.CustomerUserDTO;
import com.example.bank_management.Model.Customer;
import com.example.bank_management.Model.User;
import com.example.bank_management.Serivce.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService; 

    @GetMapping("/get-all-customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers()); 
    }


    @PostMapping("/register-customer")
    public ResponseEntity<ApiRespinse> registerCustomer(@Valid @RequestBody CustomerUserDTO customerUserDTO){
        customerService.registerCustomer(customerUserDTO);
        return  ResponseEntity.ok(new ApiRespinse("Customer added successfully")); 
    }

    @PutMapping("/update-customer")
    public ResponseEntity<ApiRespinse> updateCustomer(@AuthenticationPrincipal User user, @Valid @RequestBody CustomerUserDTO customerUserDTO){
        customerService.updateCustomer(user.getId(), customerUserDTO);
        return ResponseEntity.ok(new ApiRespinse("Customer updated successfully")); 
    }

    @DeleteMapping("/delete-customer")
    public ResponseEntity<ApiRespinse> updateCustomer(@RequestParam Integer customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(new ApiRespinse("Customer deleted successfully")); 
    }
}


