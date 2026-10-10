
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.repository.BlogRepository;
import com.project4.miniprojects4.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void approve(Long id) {
        Blog blog = getBlogById(id);

        if (blog != null) {
            blog.setApproved(true);
            blogRepository.save(blog);
        }
    }

    @Override
    public List<Blog> getApprovedBlogs() {
        return blogRepository.findByApprovedTrue();
    }
}
