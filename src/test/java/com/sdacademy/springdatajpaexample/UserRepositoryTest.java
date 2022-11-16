package com.sdacademy.springdatajpaexample;

import com.sdacademy.springdatajpaexample.model.User;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserRepositoryTest extends BaseTest {
    @Test
    void shouldCreateUser() {
        // given
        User user = new User("Jan", "Kowalski", "jkowalki@gmail.com", "password", User.Roles.ROLE_ADMIN);

        // when
        User saved = repository.save(user);

        // then
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Jan", saved.getFirstName());
        assertEquals(User.Roles.ROLE_ADMIN, saved.getRole());
    }

    @Test
    void shouldFindAllUsersByLastName() {
        // given
        // when
        List<User> users = repository.findAllByLastName("Kowalski");

        // then
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    // TODO: dodać kolejne metody repozytorium w celu przetestowania
}
