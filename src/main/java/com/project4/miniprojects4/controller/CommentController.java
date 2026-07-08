package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.model.Comment;
import com.project4.miniprojects4.service.BlogService;
import com.project4.miniprojects4.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final BlogService blogService;

    public CommentController(CommentService commentService,
                             BlogService blogService) {

        this.commentService = commentService;
        this.blogService = blogService;
    }

    @PostMapping("/blog/{id}/comment")
    public String addComment(@PathVariable Long id,
                             @RequestParam String message) {

        Blog blog = blogService.getBlogById(id);

        if (blog != null) {

            Comment comment = new Comment();

            comment.setMessage(message);
            comment.setApproved(false);
            comment.setBlog(blog);

            commentService.save(comment);
        }

        return "redirect:/blog/" + id;
    }

}