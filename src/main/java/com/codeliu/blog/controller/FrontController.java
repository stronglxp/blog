package com.codeliu.blog.controller;

import com.codeliu.blog.service.ArticleService;
import com.codeliu.blog.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/index")
    public String getIndexPage() {
        return "test";
    }

    @GetMapping("/article/info/{articleId}")
    @ResponseBody
    public ResultUtils<Map<String, Object>> articleInfo(@PathVariable Integer articleId) {
        ResultUtils<Map<String, Object>> res = new ResultUtils<>();
        if (articleId == null) {
            return res.isFaild();
        }

        res = articleService.articleInfo(articleId);

        return res;
    }
}
