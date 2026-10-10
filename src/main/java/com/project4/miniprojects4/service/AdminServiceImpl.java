
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.repository.BlogRepository;
import com.project4.miniprojects4.repository.CommentRepository;
import com.project4.miniprojects4.repository.UserRepository;
import com.project4.miniprojects4.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public AdminServiceImpl(UserRepository userRepository,
                            BlogRepository blogRepository,
                            CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }

    @Override
    public long getBlogCount() {
        return blogRepository.count();
    }

    @Override
    public long getCommentCount() {
        return commentRepository.count();
    }
}
