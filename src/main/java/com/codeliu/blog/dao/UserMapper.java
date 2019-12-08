package com.codeliu.blog.dao;

import com.codeliu.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User dao
 */
@Mapper
public interface UserMapper {

    /**
     * Call SQL to query users.
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * Add a user.
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * delete a user.
     * @param userName
     * @return
     */
    int delUser(String userName);

    /**
     * update user information.
     * @param user
     * @return
     */
    Integer updateUser(User user);
}