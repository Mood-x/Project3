package com.example.bank_management.Serivce;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bank_management.API.ApiException;
import com.example.bank_management.DTO.CustomerUserDTO;
import com.example.bank_management.Model.Customer;
import com.example.bank_management.Model.User;
import com.example.bank_management.Repository.AuthRepository;
import com.example.bank_management.Repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository; 
    private final AuthRepository authRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll(); 
    }

    public void registerCustomer(CustomerUserDTO customerUserDTO){
        String hash = new BCryptPasswordEncoder().encode(customerUserDTO.getPassword()); 
        User user = new User(); 
        user.setUsername(customerUserDTO.getUsername());
        user.setPassword(hash);
        user.setName(customerUserDTO.getName());
        user.setEmail(customerUserDTO.getEmail());
        user.setRole("CUSTOMER");
        
        Customer customer = new Customer(); 
        customer.setId(null);
        customer.setPhoneNumber(customerUserDTO.getPhoneNumber());
        customer.setUser(user);

        user.setCustomer(customer);

        authRepository.save(user); 
        customerRepository.save(customer); 
    }

    public void updateCustomer(Integer authId, CustomerUserDTO customerUserDTO){
        Customer customer = customerRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND!")); 

        if(!customer.getId().equals(authId)){
            throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY");
        }

        customer.getUser().setUsername(customerUserDTO.getUsername());
        customer.getUser().setPassword(customerUserDTO.getPassword());
        customer.getUser().setName(customerUserDTO.getName());
        customer.getUser().setEmail(customerUserDTO.getEmail());
        customer.setPhoneNumber(customerUserDTO.getPhoneNumber());

        customerRepository.save(customer); 
    }

    public void deleteCustomer(Integer customerId){
        User user = authRepository.findUserById(customerId)
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND")); 

        authRepository.delete(user);
    }
}
// 8. Block bank account 
// }
