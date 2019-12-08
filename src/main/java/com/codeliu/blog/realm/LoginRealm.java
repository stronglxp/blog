package com.codeliu.blog.realm;

import com.codeliu.blog.entity.Role;
import com.codeliu.blog.entity.User;
import com.codeliu.blog.service.RoleService;
import com.codeliu.blog.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Shiro login authentication
 */
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    Logger logger = LoggerFactory.getLogger(LoginRealm.class);
    @Autowired
    private RoleService roleService;

    /**
     * Get the identity information, which is called when authenticating
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // get username
        String username = (String)getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = null;
        // Get the information of the user corresponding to the user name
        User user = userService.findByName(username);
        if(user != null) {
            // get role information
            Role role = roleService.findById(user.getRoleId());
            info = new SimpleAuthorizationInfo();
            Set<String> r = new HashSet<>();
            if(role != null) {
                r.add(role.getRoleName());
                info.setRoles(r);
            }
        }

        return info;
    }

    /**
     * For authentication, call when login
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        // get username
        String username = (String) token.getPrincipal();

        User user = null;
        // Get the information of the user corresponding to the user name
        user = userService.findByName(username);

        logger.info("user = " + user);
        // Throw an exception if the user name does not exist
        if(user == null) {
            throw new UnknownAccountException("user does not exist!");
        }

        // Authentication passed, and an identity information is returned
        AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(),
                ByteSource.Util.bytes(user.getUserSalt()), getName());
        logger.info("info = " + info);

        return info;
    }
}
