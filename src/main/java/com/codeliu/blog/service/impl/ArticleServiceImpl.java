package com.codeliu.blog.service.impl;

import com.codeliu.blog.dao.ArticleMapper;
import com.codeliu.blog.entity.Article;
import com.codeliu.blog.service.ArticleService;
import com.codeliu.blog.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResultUtils<Map<String, Object>> addArticle(Article article) {
        ResultUtils<Map<String, Object>> res = new ResultUtils<>();
        if (article == null) {
            return res.isFaild();
        }

        Integer num = articleMapper.addArticle(article);
        if (num == 1) {
            Map<String, Object> map = new HashMap<>();
            map.put("articleId", article.getArticleId());
            res = res.isOk(map);
        } else {
            res = res.isFaild();
        }

        return res;
    }
}
