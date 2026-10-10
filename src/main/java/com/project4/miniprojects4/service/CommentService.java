
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Comment;
import java.util.List;

public interface CommentService {

    void save(Comment comment);

    List<Comment> getApprovedCommentsByBlog(Long blogId);

    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    void approveComment(Long id);

    void deleteComment(Long id);
}
