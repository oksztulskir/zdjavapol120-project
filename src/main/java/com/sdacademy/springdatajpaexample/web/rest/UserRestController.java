package com.sdacademy.springdatajpaexample.web.rest;

import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.service.UserService;
import com.sdacademy.springdatajpaexample.service.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * GET  /user, request GET http://localhost:8080/user
 * GET /user/{id}, request: GET http://localhost:8080/user/1
 * GET  /user, request GET http://localhost:8080/user/search?firstName=Jan&lastName=Kowalski
 * POST /user
 * PUT /user
 * DELETE /user/{id}
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @PatchMapping
    public User updatePassword(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/search")
    public List<User> findByFirstNAmeAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.findByFirstNameAndLastName(firstName, lastName);
    }
}
