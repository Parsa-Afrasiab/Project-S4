package com.project4.miniprojects4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.project4.miniprojects4.model.Role;
import com.project4.miniprojects4.model.User;
import com.project4.miniprojects4.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {

        if (userService.findByEmail(user.getEmail()) != null) {
            return "redirect:/register";
        }

        user.setRole(Role.USER);

        userService.save(user);

        return "redirect:/login";
    }
}