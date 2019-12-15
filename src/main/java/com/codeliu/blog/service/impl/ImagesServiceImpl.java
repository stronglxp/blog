package com.codeliu.blog.service.impl;

import com.codeliu.blog.dao.ImagesMapper;
import com.codeliu.blog.entity.Images;
import com.codeliu.blog.service.ImagesService;
import com.codeliu.blog.util.OSSUtils;
import com.codeliu.blog.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImagesServiceImpl implements ImagesService {
    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public ResultUtils<Map<String, Object>> addImage(MultipartFile file) {
        ResultUtils<Map<String, Object>> res = new ResultUtils<>();
        Images images = new Images();

        if (file == null || file.getSize() <= 0) {
            return res.isFaild();
        }

        OSSUtils ossUtils = new OSSUtils();
        try {
            String name = ossUtils.uploadImage(file);
            String imageUrl = ossUtils.getImgUrl(name);
            String[] split = imageUrl.split("\\?");
            imageUrl = split[0];
            images.setImageUrl(imageUrl);
            Integer num = imagesMapper.addImage(images);
            if (num == 1) {
                // return the image info
                Map<String, Object> data = new HashMap<>();
                data.put("imageId", images.getImageId());
                data.put("imageUrl", imageUrl);
                res = res.isOk(data);
            } else {
                res = res.isFaild();
            }
        } catch (Exception e) {
            res = res.isFaild();
        } finally {
            ossUtils.destroy();
        }
        return res;
    }
}
