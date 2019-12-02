package com.codeliu.blog.controller;

import com.codeliu.blog.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public ResultUtils getIndex() {
        ResultUtils res = new ResultUtils();
        return res.isOk();
    }

    
}
