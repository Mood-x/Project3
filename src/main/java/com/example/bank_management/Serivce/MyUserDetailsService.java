package com.example.bank_management.Serivce;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bank_management.API.ApiException;
import com.example.bank_management.Model.User;
import com.example.bank_management.Repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{
    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findByUsername(username)
            .orElseThrow(() -> new ApiException("WRONG USERNAME OR PASSWORD!")); 

        return user;  
    }

}
