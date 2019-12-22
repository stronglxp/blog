package com.codeliu.blog.service.impl;

import com.codeliu.blog.dao.ArticleMapper;
import com.codeliu.blog.entity.Article;
import com.codeliu.blog.service.ArticleService;
import com.codeliu.blog.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional
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

    @Override
    public ResultUtils<List<Map<String, Object>>> listArticle(Integer maxArticleId) {
        ResultUtils<List<Map<String, Object>>> res = new ResultUtils<>();
        List<Map<String, Object>> list = new ArrayList<>();

        list = articleMapper.listArticle(maxArticleId);
        if (list.size() < 0) {
            return res.isFaild();
        }

        res = res.isOk(list);

        return res;
    }

    @Override
    @Transactional
    public ResultUtils<Map<String, Object>> deleteArticle(Integer articleId) {
        ResultUtils<Map<String, Object>> res = new ResultUtils<>();
        if (articleId == null) {
            return res.isFaild();
        }

        Integer num = articleMapper.deleteArticle(articleId);
        if (num == 1) {
            res = res.isOk(null);
        } else {
            res = res.isFaild();
        }

        return res;
    }
}
