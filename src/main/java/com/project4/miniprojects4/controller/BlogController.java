package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.model.BlogStatus;
import com.project4.miniprojects4.service.BlogService;
import com.project4.miniprojects4.service.CategoryService;
import com.project4.miniprojects4.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BlogController {

    private final CommentService commentService;
    private final BlogService blogService;
    private final CategoryService categoryService;

    public BlogController(
            BlogService blogService,
            CommentService commentService,
            CategoryService categoryService) {

        this.blogService = blogService;
        this.commentService = commentService;
        this.categoryService = categoryService;
    }

    @GetMapping("/blog/{id}")
    public String showBlog(@PathVariable Long id, Model model) {

        Blog blog = blogService.getBlogById(id);

        if (blog == null) {
            return "redirect:/blog";
        }

        if (!blog.isApproved()
                || blog.getStatus() != BlogStatus.PUBLISHED) {
            return "redirect:/blog";
        }

        blogService.increaseViews(id);

        model.addAttribute("blog", blog);
        model.addAttribute(
                "comments",
                commentService.getApprovedCommentsByBlog(id)
        );

        return "blog";
    }

    @GetMapping("/blog")
    public String listBlogs(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) List<Long> categoryIds,
            Model model) {

        model.addAttribute(
                "blogs",
                blogService.searchBlogs(q, categoryIds)
        );

        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        model.addAttribute("q", q == null ? "" : q);

        model.addAttribute(
                "selectedCategoryIds",
                categoryIds == null ? List.of() : categoryIds
        );

        return "all-articles";
    }

    @GetMapping("/blogs/{id}/image")
    @ResponseBody
    public ResponseEntity<byte[]> getBlogImage(@PathVariable Long id) {

        Blog blog = blogService.getBlogById(id);

        if (blog == null
                || blog.getImage() == null
                || blog.getImage().length == 0) {
            return ResponseEntity.notFound().build();
        }

        String contentType = blog.getImageContentType();

        if (!MediaType.IMAGE_PNG_VALUE.equalsIgnoreCase(contentType)
                && !MediaType.IMAGE_JPEG_VALUE.equalsIgnoreCase(contentType)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(blog.getImage());
    }
}