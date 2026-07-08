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

    public List<Comment> getApprovedCommentsByBlog(Long blogId) {
        return commentRepository.findByBlogIdAndApprovedTrue(blogId);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void approveComment(Long id) {
        Comment comment = getCommentById(id);

        if (comment != null) {
            comment.setApproved(true);
            commentRepository.save(comment);
        }
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

}