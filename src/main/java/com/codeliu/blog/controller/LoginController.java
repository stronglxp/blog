package com.codeliu.blog.controller;

import com.codeliu.blog.util.MsgEnum;
import com.codeliu.blog.util.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * Processing login
     * @param userName Incoming userName
     * @param userPassword Incoming userPassword
     * @return
     */
    @PostMapping("/login")
    public ResultUtils<Map<String, String>> login(String userName, String userPassword) {
        ResultUtils<Map<String, String>> res = new ResultUtils<>();

        // Shiro implements login
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword);
        Subject subject = SecurityUtils.getSubject();

        try {
            // If the user name cannot be obtained, the login fails and an exception will be thrown
            subject.login(token);
            // admin
            if(subject.hasRole("admin")) {
                return res.isOk(null);
            } else {
                res.setCode(MsgEnum.USER_INVALID.getCode());
                res.setMsg(MsgEnum.USER_INVALID.getMsg());
                res.setData(null);
            }
        } catch (UnknownAccountException e) {
            res.setCode(MsgEnum.USER_ERROR.getCode());
            res.setMsg(MsgEnum.USER_ERROR.getMsg());
            res.setData(null);
        } catch (Exception e) {
            res.setCode(MsgEnum.PASS_ERROR.getCode());
            res.setMsg(MsgEnum.PASS_ERROR.getMsg());
            res.setData(null);
        }

        return res;
    }
}
