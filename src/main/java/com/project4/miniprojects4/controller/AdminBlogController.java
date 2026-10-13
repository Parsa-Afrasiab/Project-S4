package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project4.miniprojects4.service.CategoryService;
import com.project4.miniprojects4.service.TagService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.ArrayList;
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
    public String saveBlog(
            @ModelAttribute Blog blog,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) List<Long> tags) throws IOException {

        blog.setId(null);
        blog.setApproved(false);
        blog.setStatus(com.project4.miniprojects4.model.BlogStatus.DRAFT);
        blog.setPublishedAt(null);

        if (imageFile != null && !imageFile.isEmpty()) {
            blog.setImage(imageFile.getBytes());
            blog.setImageContentType(imageFile.getContentType());
        }

        if (categories != null) {
            blog.setCategories(categories.stream()
                    .map(id -> categoryService.getCategoryById(id))
                    .filter(category -> category != null)
                    .toList());
        }

        if (tags != null) {
            blog.setTags(tags.stream()
                    .map(id -> tagService.getTagById(id))
                    .filter(tag -> tag != null)
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

    @PostMapping("/approve/{id}")
    public String approveBlog(@PathVariable Long id) {

        blogService.approve(id);

        return "redirect:/admin/blogs";

    }

    @GetMapping("/edit/{id}")
    public String editBlog(@PathVariable Long id, Model model) {

        Blog blog = blogService.getBlogById(id);

        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("tags", tagService.getAllTags());

        return "edit-blog";
    }

    @PostMapping("/update")
    public String updateBlog(
            @ModelAttribute Blog formBlog,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) List<Long> tags) throws IOException {

        Blog blog = blogService.getBlogById(formBlog.getId());

        if (blog == null) {
            return "redirect:/admin/blogs";
        }

        blog.setTitle(formBlog.getTitle());
        blog.setAuthor(formBlog.getAuthor());
        blog.setSlug(formBlog.getSlug());
        blog.setExcerpt(formBlog.getExcerpt());
        blog.setContent(formBlog.getContent());
        if (imageFile != null && !imageFile.isEmpty()) {
            blog.setImage(imageFile.getBytes());
            blog.setImageContentType(imageFile.getContentType());
        }

        if (categories != null) {
            blog.setCategories(new ArrayList<>(
                    categories.stream()
                            .map(id -> categoryService.getCategoryById(id))
                            .filter(category -> category != null)
                            .toList()
            ));
        } else {
            blog.setCategories(new ArrayList<>());
        }

        if (tags != null) {
            blog.setTags(new ArrayList<>(
                    tags.stream()
                            .map(id -> tagService.getTagById(id))
                            .filter(tag -> tag != null)
                            .toList()
            ));
        } else {
            blog.setTags(new ArrayList<>());
        }

        blogService.save(blog);

        return "redirect:/admin/blogs";
    }
}