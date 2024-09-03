package com.example.bank_management.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerUserDTO {




    @NotEmpty(message = "Username should be not empty")
    @Size(min = 4, max = 10, message = "Username must be between 4 to 10 characters")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username; 

    @NotEmpty(message = "Password should be not empty")
    @Size(min = 6, max = 256)
    @Column(columnDefinition = "varchar(256) not null")
    private String password;


    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 to 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;


    @NotEmpty(message = "Email should be not empty")
    @NotBlank
    @Email
    @Column(columnDefinition = "varchar(40) unique not null")
    private String email; 

    @NotEmpty
    @Column(columnDefinition = "enum('CUSTOMER', 'EMPLOYEE', 'ADMIN') not null")
    private String role;

    @NotEmpty(message = "Phone number should be not empty")
    @Pattern(regexp = "^(?:05|\\+)[0-9\\s.\\/-]{6,20}$")
    private String phoneNumber; 

}
