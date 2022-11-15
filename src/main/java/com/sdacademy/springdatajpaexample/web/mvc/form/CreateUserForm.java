package com.sdacademy.springdatajpaexample.web.mvc.form;

import com.sdacademy.springdatajpaexample.model.User;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserForm {
    private String firstName;
    private String lastName;
    @NotBlank(message = "Pole nie może być puste")
    @Email(message = "Login powinien być poprawnym formatem adresu email")
    private String login;
    @Size(min = 8, message = "Minimalna liczba znaków: 8")
    @NotBlank(message = "Pole nie może być puste")
    private String password;
    private User.Roles role;
}
