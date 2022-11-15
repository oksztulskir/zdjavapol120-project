package com.sdacademy.springdatajpaexample.service;

import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public List<User> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(toList());
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found!"));
    }

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(User user) {
        User current = findById(user.getId());
        if (user.getFirstName() == null) {
            user.setFirstName(current.getFirstName());
        }
        if (user.getLastName() == null) {
            user.setLastName(current.getLastName());
        }
        if (user.getLogin() == null) {
            user.setLogin(current.getLogin());
        }
        if (user.getPassword() == null) {
            user.setPassword(current.getPassword());
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
        }

        return repository.save(user);
    }

    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return repository.findAllByFirstNameAndLastName(firstName, lastName);
    }
}
