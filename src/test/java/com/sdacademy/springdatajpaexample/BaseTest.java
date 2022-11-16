package com.sdacademy.springdatajpaexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class BaseTest {
    @Autowired
    protected UserRepository repository;
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;

    @BeforeEach
    void beforeEach() {
        repository.save(new User("Tomasz", "Kowalski", "tkowaslki@gmail.com", "password", User.Roles.ROLE_ADMIN));
        repository.save(new User("Tomasz", "Nowak", "tnowak@gmail.com", "password", User.Roles.ROLE_USER));
        repository.save(new User("Grzegorz", "Kowalski", "gkowaslki@gmail.com", "password", User.Roles.ROLE_ADMIN));
        repository.save(new User("Grzegorz", "Kowalski", "user2@gmail.com", "$2a$10$xgJvrnDvVaE29muIKD2/0eQ0Dj.eABXwHmqxLMFII6ZGT8kwhAL9O", User.Roles.ROLE_ADMIN));
    }

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }
}
