
package com.project4.miniprojects4.repository;

import com.project4.miniprojects4.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}
