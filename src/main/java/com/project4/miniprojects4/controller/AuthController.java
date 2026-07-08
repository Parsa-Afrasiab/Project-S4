package com.project4.miniprojects4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.project4.miniprojects4.model.Role;
import com.project4.miniprojects4.model.User;
import com.project4.miniprojects4.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

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
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session) {

        User user = userService.findByEmail(email);

        if (user == null) {
            return "redirect:/login";
        }

        if (!user.getPassword().equals(password)) {
            return "redirect:/login";
        }

        session.setAttribute("user", user);

        if (user.getRole() == Role.ADMIN) {
            return "redirect:/admin";
        }

        return "redirect:/";
    }

}