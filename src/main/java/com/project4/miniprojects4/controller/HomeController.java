package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.service.BlogService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final BlogService blogService;


    public HomeController(BlogService blogService) {

        this.blogService = blogService;

    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "blogs",
                blogService.getApprovedBlogs()
        );

        return "index";
    }

}