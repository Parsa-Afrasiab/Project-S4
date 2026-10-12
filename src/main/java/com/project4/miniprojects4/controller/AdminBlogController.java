package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project4.miniprojects4.service.CategoryService;
import com.project4.miniprojects4.service.TagService;
import java.util.List;

@Controller
@RequestMapping("/admin/blogs")
public class AdminBlogController {


    private final BlogService blogService;
    private final CategoryService categoryService;
    private final TagService tagService;


    public AdminBlogController(BlogService blogService,
                               CategoryService categoryService,
                               TagService tagService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
        this.tagService = tagService;
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
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("tags", tagService.getAllTags());

        return "add-blog";
    }


    @PostMapping("/save")
    public String saveBlog(@ModelAttribute Blog blog,
                           @RequestParam(required = false) List<Long> categories,
                           @RequestParam(required = false) List<Long> tags) {

        blog.setApproved(false);

        if (categories != null) {
            blog.setCategories(categories.stream()
                    .map(id -> categoryService.getCategoryById(id))
                    .toList());
        }

        if (tags != null) {
            blog.setTags(tags.stream()
                    .map(id -> tagService.getTagById(id))
                    .toList());
        }

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