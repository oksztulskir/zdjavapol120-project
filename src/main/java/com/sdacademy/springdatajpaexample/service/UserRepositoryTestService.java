package com.sdacademy.springdatajpaexample.service;

import com.sdacademy.springdatajpaexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryTestService implements CommandLineRunner {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Hash generated for user1: {}", encoder.encode("user1"));
        log.info("Hash generated for admin: {}", encoder.encode("admin"));

//        repository.save(new User("Jan", "Kowalski", "kowalskij", "test1"));
//        repository.save(new User("Jan", "Nowak", "nowakj", "test2"));
//        repository.save(new User("Katarzyna", "Kowalska", "kowalskak", "test3"));
//        repository.save(new User("Katarzyna", "Nowak", "nowakk", "test4"));

//        log.info("List of all users:");
//        printAllUsers();
//
//        log.info("Finding user by id=1:");
//        repository.findById(1L).ifPresent(user -> log.info(user.toString()));

//        log.info("Deleting user by id:");
//        repository.deleteById(1L);
//        printAllUsers();

//        repository.findById(2L).ifPresent(user -> {
//            user.setFirstName("Janusz");
//            repository.save(user);
//        });
//
//        log.info("Find all users by lastName: ");
//        repository.findAllByLastName("Nowak").forEach(user -> log.info(user.toString()));
//
//        log.info("Find all users by firstName and lastName:");
//        repository.findAllByFirstNameAndLastName("Janusz", "Nowak").forEach(user -> log.info(user.toString()));
//
//        log.info("Find by login:");
//        repository.findByLogin("nowakj").ifPresent(user -> log.info(user.toString()));
//        log.info("Find by login not existing:");
//        repository.findByLogin("nowakjj").ifPresent(user -> log.info(user.toString()));
//        log.info("Find by firstName like: ");
//        repository.findAllByFirstNameLike("____").forEach(user -> log.info(user.toString()));
//        log.info("Find top 2 by lastName:");
//        repository.findTop2ByLastNameOrderByFirstNameDesc("Nowak").forEach(user -> log.info(user.toString()));
//        log.info("Find by password:");
//        repository.findByPassword("test4").forEach(user -> log.info(user.toString()));
//        log.info("Find by login using native SQL:");
//        repository.findByLoginNative("nowakj").ifPresent(user -> log.info(user.toString()));
    }

    private void printAllUsers() {
        repository.findAll().forEach(user -> log.info(user.toString()));
    }
}
