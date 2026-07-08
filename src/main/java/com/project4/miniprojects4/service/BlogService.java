package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
    public void approve(Long id) {

        Blog blog = getBlogById(id);

        if (blog != null) {

            blog.setApproved(true);

            blogRepository.save(blog);

        }

    }
    public List<Blog> getApprovedBlogs() {

        return blogRepository.findByApprovedTrue();

    }
}