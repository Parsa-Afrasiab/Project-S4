package com.project4.miniprojects4.repository;

import com.project4.miniprojects4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByApprovedTrue();

    List<Comment> findByBlogIdAndApprovedTrueOrderByCreatedAtDesc(Long blogId);

}
