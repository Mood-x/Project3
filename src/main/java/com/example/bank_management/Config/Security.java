package com.example.bank_management.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.bank_management.Serivce.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final MyUserDetailsService myUserDetailsService; 

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); 
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider; 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authenticationProvider(daoAuthenticationProvider())
            .authorizeHttpRequests()
            .requestMatchers(
                "/api/v1/customer/register-customer",
                "/api/v1/employee/register-employee"
                ).permitAll()

            .requestMatchers(
                "/api/v1/customer/update-customer",
                "/api/v1/customer/create-account",
                "/api/v1/customer/update-account",
                "/api/v1/customer/show-account-details**",
                "/api/v1/customer/get-all-my-accounts",
                "/api/v1/account/deposit**",
                "/api/v1/account/withdraw**",
                "/api/v1/account/transfer**"
                
                ).hasAuthority("CUSTOMER") // CUSTOMER //TODO Update 
    
            .requestMatchers(
                "/api/v1/user/get-all-users",
                "/api/v1/user/get-users-by-role**",
                "/api/v1/user/register-admin",
                "/api/v1/user/update-admin**",
                "/api/v1/user/delete-admin**", 
                "/api/v1/customer/get-all-customers",
                "/api/v1/customer/delete-customer**",
                "/api/v1/employee/get-all-employees", 
                "/api/v1/employee/delete-employee**", 
                "/api/v1/account/get-all-accounts", 
                "/api/v1/account/switch-active-status**", 
                "/api/v1/account/block-account**"
                ).hasAuthority("ADMIN") // ADMIN

            .requestMatchers(
                "/api/v1/employee/update-employee"
                ).hasAuthority("EMPLOYEE") // EMPLOYEE //TODO Update

            .anyRequest().authenticated()
            .and()
            .logout().logoutUrl("/api/v1/auth/logout")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .and()
            .httpBasic();
        return http.build(); 
    }
}
