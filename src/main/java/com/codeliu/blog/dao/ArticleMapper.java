package com.codeliu.blog.dao;

import com.codeliu.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * article dao
 */
@Mapper
public interface ArticleMapper {
    Integer addArticle(Article article);

    List<Map<String, Object>> listArticle(Integer maxArticleId);
}
