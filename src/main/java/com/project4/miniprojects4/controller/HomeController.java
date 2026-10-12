package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.service.BlogService;
import com.project4.miniprojects4.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class HomeController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    public HomeController(BlogService blogService,
                          CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {

        model.addAttribute("blogs", blogService.getApprovedBlogs());

        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        boolean isAdmin = authentication != null &&
                authentication.getAuthorities().stream()
                        .anyMatch(authority ->
                                authority.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);

        return "index";
    }
}