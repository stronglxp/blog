package com.codeliu.blog.service;

import com.codeliu.blog.entity.User;

public interface UserService {

    User findByName(String name);

    int addUser(User user);

    int delUser(String userName);

    Integer updateUser(User user);
}
