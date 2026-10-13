package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Category;
import com.project4.miniprojects4.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.project4.miniprojects4.model.Blog;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found: " + id));

        for (Blog blog : category.getBlogs()) {
            blog.getCategories().removeIf(
                    c -> c.getId().equals(id)
            );
        }

        categoryRepository.delete(category);
    }
}
