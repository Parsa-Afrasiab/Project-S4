package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/blogs")
public class AdminBlogController {


    private final BlogService blogService;


    public AdminBlogController(BlogService blogService) {

        this.blogService = blogService;

    }


    @GetMapping
    public String blogs(Model model) {

        model.addAttribute(
                "blogs",
                blogService.getAllBlogs()
        );

        return "manage-blogs";

    }


    @GetMapping("/add")
    public String addBlog(Model model) {

        model.addAttribute(
                "blog",
                new Blog()
        );

        return "add-blog";

    }


    @PostMapping("/save")
    public String saveBlog(@ModelAttribute Blog blog) {

        blog.setApproved(false);

        blogService.save(blog);

        return "redirect:/admin/blogs";

    }


    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {

        blogService.delete(id);

        return "redirect:/admin/blogs";

    }

    @GetMapping("/approve/{id}")
    public String approveBlog(@PathVariable Long id) {

        blogService.approve(id);

        return "redirect:/admin/blogs";

    }
}