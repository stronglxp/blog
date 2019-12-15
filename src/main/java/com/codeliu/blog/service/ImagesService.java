package com.codeliu.blog.service;

import com.codeliu.blog.util.ResultUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImagesService {

    ResultUtils<Map<String, Object>> addImage(MultipartFile file);

    ResultUtils<Map<String, Object>> deleteImage(Integer imageId);
}
