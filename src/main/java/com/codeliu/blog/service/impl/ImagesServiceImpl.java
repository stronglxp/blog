package com.codeliu.blog.service.impl;

import com.codeliu.blog.dao.ImagesMapper;
import com.codeliu.blog.entity.Images;
import com.codeliu.blog.service.ImagesService;
import com.codeliu.blog.util.MsgEnum;
import com.codeliu.blog.util.OSSUtils;
import com.codeliu.blog.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImagesServiceImpl implements ImagesService {
    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public ResultUtils<Map<String, Object>> deleteImage(Integer imageId) {
        ResultUtils<Map<String, Object>> res = new ResultUtils<>();
        if (imageId == null) {
            res.setCode(MsgEnum.PARAM_ERROR.getCode());
            res.setMsg(MsgEnum.PARAM_ERROR.getMsg());
            res.setData(null);
            return res;
        }

        Integer num = imagesMapper.deleteImage(imageId);
        if (num == 1) {
            res = res.isOk(null);
        } else {
            res = res.isFaild();
        }

        return res;
    }

    @Override
    public ResultUtils<List<Map<String, Object>>> listImage(Integer maxImageId) {
        ResultUtils<List<Map<String, Object>>> res = new ResultUtils<>();
        List<Map<String, Object>> list = new ArrayList<>();

        list = imagesMapper.listImage(maxImageId);
        if (list.size() < 0) {
            return res.isFaild();
        }

        res = res.isOk(list);

        return res;
    }
}
