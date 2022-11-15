package com.sdacademy.springdatajpaexample.web.mvc;

import com.sdacademy.springdatajpaexample.service.UserService;
import com.sdacademy.springdatajpaexample.web.mvc.form.CreateUserForm;
import com.sdacademy.springdatajpaexample.web.mvc.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/user")
public class UserController {
    private static final String MESSAGE_KEY = "message";

    private final UserService userService;

    @GetMapping
    public String create(ModelMap map) {
        map.addAttribute("user", new CreateUserForm());
        return "create-user";
    }

    @PostMapping
    public String handleCreate(@ModelAttribute("user") @Valid CreateUserForm form, Errors errors, RedirectAttributes redirectAttributes) {
        log.info("Creating user from form: {}", form);
        if (errors.hasErrors()) {
            return "create-user";
        }
        userService.save(UserMapper.toEntity(form));
        redirectAttributes.addAttribute(MESSAGE_KEY, "Użytkownik o loginie: " + form.getLogin() + " został pomyślnie dodany!");

        return "redirect:/mvc/user/list";
    }

    @GetMapping("/list")
    public String list(ModelMap map, @ModelAttribute(MESSAGE_KEY) String message) {
        map.addAttribute("users", userService.findAll());
        if (!message.isBlank()) {
            map.addAttribute(MESSAGE_KEY, message);
        }
        return "user-list";
    }
}
