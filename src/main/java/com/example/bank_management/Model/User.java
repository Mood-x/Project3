package com.example.bank_management.Model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role)); 
    }

    @Override
    public boolean isAccountNonExpired(){
        return true; 
    }

    @Override
    public boolean isAccountNonLocked(){
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true; 
    }

    @Override
    public boolean isEnabled(){
        return true; 
    }


    
    // Relations 

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee; 

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;



}
