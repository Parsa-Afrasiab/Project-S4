
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Comment;
import com.project4.miniprojects4.repository.CommentRepository;
import com.project4.miniprojects4.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getApprovedCommentsByBlog(Long blogId) {
        return commentRepository.findByBlogIdAndApprovedTrue(blogId);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void approveComment(Long id) {
        Comment comment = getCommentById(id);

        if (comment != null) {
            comment.setApproved(true);
            commentRepository.save(comment);
        }
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
