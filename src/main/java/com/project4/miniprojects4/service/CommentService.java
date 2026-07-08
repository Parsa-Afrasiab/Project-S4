package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Comment;
import com.project4.miniprojects4.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}