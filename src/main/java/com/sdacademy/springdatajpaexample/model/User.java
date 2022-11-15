package com.sdacademy.springdatajpaexample.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class User {
    public User(String firstName, String lastName, String login, String password, Roles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String firstName;

    @Setter
    @Column(nullable = false)
    private String lastName;

    @Setter
    @Column(nullable = false, unique = true)
    private String login;

    @Setter
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public enum Roles {
        ROLE_USER, ROLE_ADMIN
    }
}
