package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService) {

        this.adminService = adminService;

    }


    @GetMapping("/admin")
    public String admin(Model model) {


        model.addAttribute(
                "userCount",
                adminService.getUserCount()
        );


        model.addAttribute(
                "blogCount",
                adminService.getBlogCount()
        );


        model.addAttribute(
                "commentCount",
                adminService.getCommentCount()
        );


        return "admin";

    }

}