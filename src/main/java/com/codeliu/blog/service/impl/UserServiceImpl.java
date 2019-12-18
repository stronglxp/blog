package com.codeliu.blog.service.impl;

import com.codeliu.blog.dao.UserMapper;
import com.codeliu.blog.entity.User;
import com.codeliu.blog.service.UserService;
import com.codeliu.blog.util.DataUtils;
import com.codeliu.blog.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public ResultUtils<Map<String, Object>> updateUser(User user) {
        ResultUtils<Map<String, Object>> res = new ResultUtils<>();
        if (user == null) {
            return res.isFaild();
        }

        String password = user.getUserPassword();
        String salt = DataUtils.getSalt();
        user.setUserPassword(DataUtils.getMD5Str(password, salt));
        user.setUserSalt(salt);

        Integer num = userMapper.updateUser(user);
        if (num == 1) {
            res = res.isOk(null);
        } else {
            res = res.isFaild();
        }

        return res;
    }
}
