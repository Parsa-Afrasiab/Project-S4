package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.User;
import com.project4.miniprojects4.repository.UserRepository;
import com.project4.miniprojects4.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final AdminService adminService;
    private final UserRepository userRepository;

    public AdminUserController(
            AdminService adminService,
            UserRepository userRepository) {
        this.adminService = adminService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String manageUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "manage-users";
    }

    @PostMapping("/{id}/enable")
    public String enableUser(@PathVariable Long id) {
        adminService.enableUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}/disable")
    public String disableUser(@PathVariable Long id) {
        adminService.disableUser(id);
        return "redirect:/admin/users";
    }
}
