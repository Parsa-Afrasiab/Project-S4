package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Category;
import com.project4.miniprojects4.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String manageCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "manage-categories";
    }

    @PostMapping("/save")
    public String saveCategory(
            @RequestParam String name,
            @RequestParam(required = false) String description) {

        Category category = new Category(name, description);
        categoryService.save(category);

        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);

        return "redirect:/admin/categories";
    }
}
