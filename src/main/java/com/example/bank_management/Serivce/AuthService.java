package com.example.bank_management.Serivce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank_management.API.ApiException;
import com.example.bank_management.Model.User;
import com.example.bank_management.Repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository; 

    public List<User> getAllUsers(){
        return authRepository.findAll();
    }

    public List<User> getAllUsersByRole(String role){
        List<User> users = authRepository.findAllByRole(role)
            .orElseThrow(() -> new ApiException("USERS NOT FOUND!")); 

        return users; 
    }

    // public void registerAdmin(User user){
    //     user.setRole("ADMIN");
    //     String hash = new BCryptPasswordEncoder().encode(user.getPassword()); 
    //     user.setPassword(hash);

    //     authRepository.save(user); 
    // }

    // public void updateAdmin(Integer authId, Integer adminId, User updateUser){
    //     User user = authRepository.findUserById(adminId)
    //         .orElseThrow(() -> new ApiException("USER NOT FOUND!")); 

    //     if(user.getId().equals(authId)){
    //         throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
    //     }

    //     user.setUsername(updateUser.getUsername());
    //     user.setPassword(updateUser.getPassword());
    //     user.setName(updateUser.getName());
    //     user.setEmail(updateUser.getEmail());

    //     authRepository.save(user); 
    // }

    
    // public void deleteAdmin(Integer authId, Integer adminId){
    //     User user = authRepository.findUserById(adminId)
    //         .orElseThrow(() -> new ApiException("USER NOT FOUND!")); 

    //     if(user.getId().equals(authId)){
    //         throw new ApiException("SORRY YOU DON'T HAVE AUTHORITY"); 
    //     }
    //     authRepository.delete(user); 
    // }
}
