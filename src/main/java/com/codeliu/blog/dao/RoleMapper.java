package com.codeliu.blog.dao;

import com.codeliu.blog.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * Role dao.
 */
@Mapper
public interface RoleMapper {

    /**
     * Find role by ID
     * @param roleId
     * @return
     */
    Role findById(Integer roleId);
}