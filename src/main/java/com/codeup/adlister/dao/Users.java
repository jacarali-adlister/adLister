package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    User findByUserId(long userId);
    Long insert(User user);
    void updateprofile(User user, Long id);
    List<User> allUsers();
    void deleteUser(Long id);
    void makeAdmin (User user);

}