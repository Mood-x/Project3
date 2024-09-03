package com.example.bank_management.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_management.API.ApiRespinse;
import com.example.bank_management.DTO.AccountCustomerDTO;
import com.example.bank_management.Model.Account;
import com.example.bank_management.Model.User;
import com.example.bank_management.Serivce.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService; 

    @GetMapping("/get-all-accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts()); 
    }

    @PostMapping("/create-account")
    public ResponseEntity<ApiRespinse> createAccount(@Valid @RequestBody AccountCustomerDTO accountCustomerDTO){
        accountService.createAccount(accountCustomerDTO);
        return ResponseEntity.ok(new ApiRespinse("Account added successfully")); 
    }

    @PutMapping("/update-account")
    public ResponseEntity<ApiRespinse> updateAccount(@AuthenticationPrincipal User user, @Valid @RequestBody AccountCustomerDTO accountCustomerDTO){
        accountService.updateAccount(user.getId(), accountCustomerDTO);
        return ResponseEntity.ok(new ApiRespinse("Account updated successfully")); 
    }

    @PutMapping("/switch-active-status")
    public ResponseEntity<ApiRespinse> switchActiveStatus(@RequestParam Integer accountId){
        accountService.switchActiveStatus(accountId); 
        return ResponseEntity.ok(new ApiRespinse("Account is active"));
    }

    @GetMapping("/show-account-details")
    public ResponseEntity<Account> showAccountDetails(@AuthenticationPrincipal User user, @RequestParam Integer accountId){
        return ResponseEntity.ok(accountService.showAccountDetails(user.getId(), accountId)); 
    }

    @GetMapping("/get-all-my-accounts")
    public ResponseEntity<List<Account>> getMyAccounts(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(accountService.getMyAccounts(user.getId())); 
    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity<ApiRespinse> deposit(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @PathVariable double amount){
        accountService.deposit(user.getId(), accountId, amount);
        return ResponseEntity.ok(new ApiRespinse("Dopsit: " + amount + " to " + user.getUsername()  + ", Successfully")); 
    }

    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity<ApiRespinse> withdraw(@AuthenticationPrincipal User user, @PathVariable Integer accountId, @PathVariable double amount){
        accountService.withdraw(user.getId(), accountId, amount);
        return ResponseEntity.ok(new ApiRespinse("Withdraw: " + amount + " from " + user.getUsername()  + ", Successfully")); 
    }

    @PostMapping("/transfer/{fromAccount}/{toAccount}/{amount}")
    public ResponseEntity<ApiRespinse> transfer(@AuthenticationPrincipal User user, @PathVariable Integer fromAccount, @PathVariable Integer toAccount, @PathVariable double amount){
        accountService.transfer(user.getId(), fromAccount, toAccount, amount);
        return ResponseEntity.ok(new ApiRespinse("Transfer " + amount + " from " + fromAccount + " to " + toAccount)); 
    }

    @PutMapping("/block-account")
    public ResponseEntity<ApiRespinse> blockAccount(@RequestParam Integer accountId){
        accountService.blockAccount(accountId);
        return ResponseEntity.ok(new ApiRespinse("Blocked account successfully")); 
    }
}
