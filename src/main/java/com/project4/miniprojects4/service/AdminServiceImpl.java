
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.Role;
import com.project4.miniprojects4.model.User;
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

    @Override
    public void enableUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(true);
        userRepository.save(user);
    }


    @Override
    public void disableUser(Long id, String currentUsername) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getUsername().equals(currentUsername)) {
            throw new IllegalArgumentException(
                    "You cannot disable your own account");
        }

        if (user.getRole() == Role.ADMIN && user.isEnabled()) {

            long activeAdmins = userRepository.findAll().stream()
                    .filter(u -> u.getRole() == Role.ADMIN)
                    .filter(User::isEnabled)
                    .count();

            if (activeAdmins <= 1) {
                throw new IllegalArgumentException(
                        "Cannot disable the last active admin");
            }
        }

        user.setEnabled(false);
        userRepository.save(user);
    }

}
