
package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.model.Comment;
import com.project4.miniprojects4.model.User;
import com.project4.miniprojects4.service.BlogService;
import com.project4.miniprojects4.service.CommentService;
import com.project4.miniprojects4.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final BlogService blogService;
    private final UserService userService;

    public CommentController(
            CommentService commentService,
            BlogService blogService,
            UserService userService) {

        this.commentService = commentService;
        this.blogService = blogService;
        this.userService = userService;
    }

    @PostMapping("/blog/{id}/comment")
    public String addComment(
            @PathVariable Long id,
            @RequestParam String message,
            Authentication authentication) {

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName().equals("anonymousUser")) {
            return "redirect:/login";
        }

        Blog blog = blogService.getBlogById(id);

        if (blog == null) {
            return "redirect:/";
        }

        User user = userService.findByEmail(authentication.getName());

        if (user == null) {
            return "redirect:/login";
        }

        Comment comment = new Comment();

        comment.setMessage(message);
        comment.setAuthorName(user.getFullName());
        comment.setAuthorEmail(user.getEmail());
        comment.setApproved(false);
        comment.setBlog(blog);

        commentService.save(comment);

        return "redirect:/blog/" + id;
    }
}
