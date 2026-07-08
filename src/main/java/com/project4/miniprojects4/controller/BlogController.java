package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.service.BlogService;
import com.project4.miniprojects4.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogController {

    private final CommentService commentService;
    private final BlogService blogService;


    public BlogController(BlogService blogService,
                          CommentService commentService) {

        this.blogService = blogService;
        this.commentService = commentService;

    }


    @GetMapping("/blog/{id}")
    public String showBlog(@PathVariable Long id, Model model) {

        Blog blog = blogService.getBlogById(id);

        model.addAttribute("blog", blog);

        model.addAttribute(
                "comments",
                commentService.getApprovedCommentsByBlog(id)
        );

        return "blog";
    }

}