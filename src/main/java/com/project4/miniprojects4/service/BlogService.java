
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Blog;
import java.util.List;

public interface BlogService {

    void save(Blog blog);

    List<Blog> getAllBlogs();

    Blog getBlogById(Long id);

    void delete(Long id);

    void approve(Long id);

    List<Blog> getApprovedBlogs();

    Blog getBlogBySlug(String slug);

    void increaseViews(Long id);

    List<Blog> searchBlogs(String slug, List<Long> categoryIds);
}
