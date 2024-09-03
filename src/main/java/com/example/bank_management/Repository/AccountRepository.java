package com.example.bank_management.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_management.Model.Account;
import com.example.bank_management.Model.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    Optional<Account> findAccountById(Integer id);
    List<Account> findAllByCustomer(Customer customer); 
}
