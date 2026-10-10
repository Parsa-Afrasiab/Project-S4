
package com.project4.miniprojects4.service;

import com.project4.miniprojects4.model.User;
import java.util.List;

public interface UserService {

    void save(User user);

    User findByEmail(String email);

    List<User> getAllUsers();
}
