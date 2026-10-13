package com.project4.miniprojects4.service;

public interface AdminService {

    long getUserCount();

    long getBlogCount();

    long getCommentCount();

    void enableUser(Long id);

    void disableUser(Long id);
}