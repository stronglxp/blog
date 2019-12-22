package com.codeliu.blog.controller;

import com.codeliu.blog.service.ArticleService;
import com.codeliu.blog.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public ResultUtils<List<Map<String, Object>>> getIndex() {
        return articleService.listAllArticle();
    }

}
