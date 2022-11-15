package com.sdacademy.springdatajpaexample.service.auth;

import com.sdacademy.springdatajpaexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new RuntimeException("User with login: " + username + " not found in database!"));
    }
}
