package com.example.bank_management.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_management.Model.User;
import com.example.bank_management.Serivce.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; 


    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers()); 
    }

    @GetMapping("/get-users-by-role")
    public ResponseEntity<List<User>> getAllUsersByRole(@RequestParam String role){
        return ResponseEntity.ok(authService.getAllUsersByRole(role));
    }

    // @PostMapping("/register-admin")
    // public ResponseEntity<ApiRespinse> registerAdmin(@Valid @RequestBody User user){
    //     authService.registerAdmin(user);
    //     return ResponseEntity.ok(new ApiRespinse("Admin added successfully")); 
    // }

    // @PutMapping("/update-admin")
    // public ResponseEntity<ApiRespinse> updateAdmin(@AuthenticationPrincipal User auth, @RequestParam Integer adminId, @Valid @RequestBody User user){
    //     authService.updateAdmin(auth.getId(), adminId, user);
    //     return ResponseEntity.ok(new ApiRespinse("Admin updated successfully")); 
    // }

    // @DeleteMapping("/delete-admin")
    // public ResponseEntity<ApiRespinse> deleteAdmin(@AuthenticationPrincipal User auth, @RequestParam Integer adminId){
    //     authService.deleteAdmin(auth.getId(), adminId);
    //     return ResponseEntity.ok(new ApiRespinse("Admin deleted successfully")); 
    // }
}
