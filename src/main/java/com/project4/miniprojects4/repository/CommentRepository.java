package com.project4.miniprojects4.repository;

import com.project4.miniprojects4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
