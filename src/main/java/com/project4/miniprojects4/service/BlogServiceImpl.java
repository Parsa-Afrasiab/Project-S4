
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Blog;
import com.project4.miniprojects4.model.BlogStatus;
import com.project4.miniprojects4.repository.BlogRepository;
import com.project4.miniprojects4.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    private final CommentRepository commentRepository;

    public BlogServiceImpl(BlogRepository blogRepository,
                           CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
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
    @Transactional
    public void delete(Long id) {
        if (blogRepository.existsById(id)) {
            commentRepository.deleteByBlogId(id);
            blogRepository.deleteById(id);
        }
    }


    @Override
    public void approve(Long id) {
        Blog blog = getBlogById(id);

        if (blog != null) {
            blog.setApproved(true);
            blog.setStatus(BlogStatus.PUBLISHED);

            if (blog.getPublishedAt() == null) {
                blog.setPublishedAt(LocalDateTime.now());
            }

            blogRepository.save(blog);
        }
    }


    @Override
    public List<Blog> getApprovedBlogs() {
        return blogRepository.findByStatusAndApprovedTrue(BlogStatus.PUBLISHED);
    }

    @Override
    public Blog getBlogBySlug(String slug) {
        return blogRepository.findBySlug(slug);
    }

    @Override
    public List<Blog> searchBlogs(String title) {
        return blogRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void increaseViews(Long id) {
        Blog blog = getBlogById(id);

        if (blog != null) {
            blog.setViews(blog.getViews() + 1);
            blogRepository.save(blog);
        }
    }
}
