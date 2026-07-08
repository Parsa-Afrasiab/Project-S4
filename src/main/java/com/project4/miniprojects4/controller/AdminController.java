package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.service.CommentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.project4.miniprojects4.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final CommentService commentService;


    public AdminController(AdminService adminService,
                           CommentService commentService) {

        this.adminService = adminService;
        this.commentService = commentService;

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

    @GetMapping("/admin/comments")
    public String manageComments(Model model) {

        model.addAttribute(
                "comments",
                commentService.getAllComments()
        );

        return "manage-comments";
    }

    @PostMapping("/admin/comments/approve/{id}")
    public String approveComment(@PathVariable Long id) {

        commentService.approveComment(id);

        return "redirect:/admin/comments";
    }

    @PostMapping("/admin/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);

        return "redirect:/admin/comments";
    }

}