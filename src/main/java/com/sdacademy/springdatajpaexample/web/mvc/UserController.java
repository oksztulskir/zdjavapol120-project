package com.sdacademy.springdatajpaexample.web.mvc;

import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.service.UserService;
import com.sdacademy.springdatajpaexample.service.auth.CustomUserDetails;
import com.sdacademy.springdatajpaexample.web.mvc.form.CreateUserForm;
import com.sdacademy.springdatajpaexample.web.mvc.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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
        map.addAttribute("roles", User.Roles.values());
        return "create-user";
    }

    @PostMapping
    public String handleCreate(@ModelAttribute("user") @Valid CreateUserForm form, Errors errors, RedirectAttributes redirectAttributes, ModelMap model) {
        log.info("Creating user from form: {}", form);
        if (errors.hasErrors()) {
            model.addAttribute("roles", User.Roles.values());
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

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, ModelMap map) {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedUsername = principal.getUsername();
        String authority = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        User foundUser = userService.findById(id);
        if (authority != null && authority.equalsIgnoreCase(User.Roles.ROLE_USER.name()) && !loggedUsername.equalsIgnoreCase(foundUser.getLogin())) {
            throw new RuntimeException("You can update only your own data!");
        }

        log.info("Validation successful!");
        map.addAttribute("user", foundUser);

        return "update-user";
    }
}
