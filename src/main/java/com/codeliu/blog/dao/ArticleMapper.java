package com.codeliu.blog.dao;

import com.codeliu.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * article dao
 */
@Mapper
public interface ArticleMapper {
    Integer addArticle(Article article);
}
