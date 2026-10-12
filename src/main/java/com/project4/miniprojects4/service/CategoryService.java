
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Category;
import java.util.List;

public interface CategoryService {

    void save(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    void deleteCategory(Long id);
}
