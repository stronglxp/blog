package com.codeliu.blog.service;

import com.codeliu.blog.entity.User;
import com.codeliu.blog.util.ResultUtils;

import java.util.Map;

public interface UserService {

    User findByName(String name);

    int addUser(User user);

    int delUser(String userName);

    ResultUtils<Map<String, Object>> updateUser(User user);
}
