
package com.project4.miniprojects4.repository;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.model.BlogStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByApprovedTrue();

    List<Blog> findByStatusAndApprovedTrue(BlogStatus status);

    Blog findBySlug(String slug);

    List<Blog> findByTitleContainingIgnoreCase(String title);
}

