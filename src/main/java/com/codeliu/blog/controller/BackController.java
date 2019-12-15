package com.codeliu.blog.controller;

import com.codeliu.blog.service.ImagesService;
import com.codeliu.blog.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("/image/upload")
    public ResultUtils<Map<String, Object>> uploadImage(MultipartFile file) {
        ResultUtils<Map<String, Object>> res;
        res = imagesService.addImage(file);
        logger.debug("res = {}", res);
        return res;
    }
}
