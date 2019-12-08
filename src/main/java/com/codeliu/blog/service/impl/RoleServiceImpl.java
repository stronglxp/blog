package com.codeliu.blog.service.impl;

import com.codeliu.blog.dao.RoleMapper;
import com.codeliu.blog.entity.Role;
import com.codeliu.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findById(Integer roleId) {
        if(roleId == null) {
            return null;
        }

        Role role = roleMapper.findById(roleId);

        return role;
    }
}
