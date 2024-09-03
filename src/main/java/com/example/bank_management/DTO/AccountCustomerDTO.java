package com.example.bank_management.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountCustomerDTO {


    private Integer customer_id; 

    @NotEmpty
    @Pattern(regexp = "(\\d){4}(-)(\\d){4}(-)(\\d){4}(-)(\\d){4}")
    private String accountNumber;

    @NotNull
    @PositiveOrZero
    private double balance;

    @Column(columnDefinition = "boolean default false")
    private boolean isActive = false; 
}
