package com.codeliu.blog.service.impl;

import com.codeliu.blog.dao.UserMapper;
import com.codeliu.blog.entity.User;
import com.codeliu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        // Parameter error returned directly
        if(name == null || "".equals(name)) {
            return null;
        }

        // Call sql to get datas
        User user = userMapper.findByName(name);

        return user;
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int delUser(String userName) {
        return userMapper.delUser(userName);
    }

    @Override
    public Integer updateUser(User user) {

        Integer result = null;
        result = userMapper.updateUser(user);
        return result;
    }
}
