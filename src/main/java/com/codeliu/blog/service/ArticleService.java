package com.codeliu.blog.service;

import com.codeliu.blog.entity.Article;
import com.codeliu.blog.util.ResultUtils;

import java.util.Map;

public interface ArticleService {
    ResultUtils<Map<String, Object>> addArticle(Article article);
}