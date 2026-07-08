package com.project4.miniprojects4.service;

import com.project4.miniprojects4.repository.BlogRepository;
import com.project4.miniprojects4.repository.CommentRepository;
import com.project4.miniprojects4.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;


    public AdminService(UserRepository userRepository,
                        BlogRepository blogRepository,
                        CommentRepository commentRepository) {

        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;

    }


    public long getUserCount() {

        return userRepository.count();

    }


    public long getBlogCount() {

        return blogRepository.count();

    }


    public long getCommentCount() {

        return commentRepository.count();

    }

}