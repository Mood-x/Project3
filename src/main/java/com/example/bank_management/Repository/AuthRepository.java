package com.example.bank_management.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_management.Model.User;


@Repository
public interface AuthRepository extends JpaRepository<User, Integer>{

    Optional<User> findUserById(Integer id); 
    Optional<User> findByUsername(String name);
    Optional<List<User>> findAllByRole(String role); 

}
