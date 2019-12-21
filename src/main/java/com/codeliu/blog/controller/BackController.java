package com.codeliu.blog.controller;

import com.codeliu.blog.entity.Article;
import com.codeliu.blog.entity.User;
import com.codeliu.blog.service.ArticleService;
import com.codeliu.blog.service.ImagesService;
import com.codeliu.blog.service.UserService;
import com.codeliu.blog.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Back-end Control
 */
@RestController
@RequestMapping("/back")
public class BackController {
    private Logger logger = LoggerFactory.getLogger(BackController.class);
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @PostMapping("/image/upload")
    public ResultUtils<Map<String, Object>> uploadImage(MultipartFile file) {
        ResultUtils<Map<String, Object>> res;
        res = imagesService.addImage(file);
        logger.debug("res = {}", res);
        return res;
    }

    @PostMapping("/image/delete")
    public ResultUtils<Map<String, Object>> deleteImage(Integer imageId) {
        return imagesService.deleteImage(imageId);
    }

    @GetMapping("/image/list")
    public ResultUtils<List<Map<String, Object>>> listImage() {
        return imagesService.listImage(null);
    }

    @GetMapping("/image/list/more")
    public ResultUtils<List<Map<String, Object>>> listImageMore(Integer maxImageId) {
        return imagesService.listImage(maxImageId);
    }

    @PostMapping("/userinfo/update")
    public ResultUtils<Map<String, Object>> updateUser(User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/article/add")
    public ResultUtils<Map<String, Object>> addArticle(Article article) {
        return articleService.addArticle(article);
    }

    @GetMapping("/article/list")
    public ResultUtils<List<Map<String, Object>>> listArticle(Integer maxArticleId) {
        return articleService.listArticle(maxArticleId);
    }
}
