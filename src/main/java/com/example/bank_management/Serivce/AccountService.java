package com.example.bank_management.Serivce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank_management.API.ApiException;
import com.example.bank_management.DTO.AccountCustomerDTO;
import com.example.bank_management.Model.Account;
import com.example.bank_management.Model.Customer;
import com.example.bank_management.Repository.AccountRepository;
import com.example.bank_management.Repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository; 

    public List<Account> getAllAccounts(){
        return accountRepository.findAll(); 
    }

    public void createAccount(AccountCustomerDTO accountCustomerDTO){
        Customer customer = customerRepository.findUserById(accountCustomerDTO.getCustomer_id())
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND!")); 

        Account account = new Account();
        account.setId(accountCustomerDTO.getCustomer_id());
        account.setAccountNumber(accountCustomerDTO.getAccountNumber());
        account.setBalance(accountCustomerDTO.getBalance());
        account.setActive(accountCustomerDTO.isActive());
        account.setCustomer(customer);
        
        accountRepository.save(account); 
    }


    public void updateAccount(Integer authId, AccountCustomerDTO accountCustomerDTO){
        Customer customer = customerRepository.findUserById(accountCustomerDTO.getCustomer_id())
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND!"));


        Account account = accountRepository.findAccountById(authId)
            .orElseThrow(() -> new ApiException("ACCOUNT NOT FOUND!")); 


        if(!account.getCustomer().getId().equals(authId)){
            throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
        }

        account.setAccountNumber(accountCustomerDTO.getAccountNumber());
        account.setBalance(accountCustomerDTO.getBalance());
        account.setActive(accountCustomerDTO.isActive());
    }

    
    public void switchActiveStatus(Integer accountId){
        Account account = accountRepository.findAccountById(accountId)
            .orElseThrow(() -> new ApiException("ACCOUNT NOT FOUND!"));
            
        account.setActive(true);
        accountRepository.save(account); 
    }


    public Account showAccountDetails(Integer authId, Integer accountId){
        Customer customer = customerRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND!")); 
    
        Account account = accountRepository.findAccountById(accountId)
            .orElseThrow(() -> new ApiException("ACCOUNT NOT FOUND!"));

        
        if(!account.getCustomer().getId().equals(authId)){
            throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
        }

        return account; 
    }

    public List<Account> getMyAccounts(Integer authId){
        Customer customer = customerRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND!")); 

        return accountRepository.findAllByCustomer(customer); 
    }

    public void deposit(Integer authId, Integer accountId, double amount){
        Customer customer = customerRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND!")); 

        Account account = accountRepository.findAccountById(accountId)
            .orElseThrow(() -> new ApiException("ACCOUNT NOT FOUND!")); 

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account); 
            
    }


    public void withdraw(Integer authId, Integer accountId, double amount){
        Customer customer = customerRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND!")); 

        Account account = accountRepository.findAccountById(accountId)
            .orElseThrow(() -> new ApiException("ACCOUNT NOT FOUND!")); 

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account); 
    }

    public void transfer(Integer authId, Integer fromAccount, Integer toAccount, double amount){
        Customer customer = customerRepository.findUserById(authId)
            .orElseThrow(() -> new ApiException("CUSTOMER NOT FOUND!")); 

        Account account1 = accountRepository.findAccountById(fromAccount)
            .orElseThrow(() -> new ApiException("ACCOUNT NOT FOUND!")); 

        Account account2 = accountRepository.findAccountById(toAccount)
            .orElseThrow(() -> new ApiException("ACCOUNT NOT FOUND!"));

        account1.setBalance(account1.getBalance() - amount);
        account2.setBalance(account2.getBalance() + amount);

        accountRepository.save(account1);
        accountRepository.save(account2);
    }

    public void blockAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId)
            .orElseThrow(() -> new ApiException("ACCOUNT NOT FOUND!")); 

        account.setActive(false);
        accountRepository.save(account); 
    }
}

