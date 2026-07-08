package com.project4.miniprojects4.repository;

import com.project4.miniprojects4.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
