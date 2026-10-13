
package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Role;
import com.project4.miniprojects4.model.User;
import com.project4.miniprojects4.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UserService userService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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
    public String registerUser(
            @org.springframework.web.bind.annotation.RequestParam String fullName,
            @org.springframework.web.bind.annotation.RequestParam String username,
            @org.springframework.web.bind.annotation.RequestParam String email,
            @org.springframework.web.bind.annotation.RequestParam String password,
            @org.springframework.web.bind.annotation.RequestParam String confirmPassword) {

        if (userService.findByEmail(email) != null) {
            return "redirect:/register?error=email";
        }

        if (userService.findByUsername(username) != null) {
            return "redirect:/register?error=username";
        }

        if (!password.equals(confirmPassword)) {
            return "redirect:/register?error=password";
        }

        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        user.setEnabled(true);

        userService.save(user);

        return "redirect:/login?registered";
    }

}
