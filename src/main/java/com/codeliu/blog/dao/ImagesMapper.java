package com.codeliu.blog.dao;

import com.codeliu.blog.entity.Images;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Imgaes dao
 */
@Mapper
public interface ImagesMapper {

    /**
     * insert a image to db
     * @param images
     * @return
     */
    Integer addImage(Images images);

    Integer deleteImage(Integer imageId);

    /**
     * Take ten pictures at a time. The field name is key and the field value is value
     * @return
     */
    List<Map<String, Object>> listImage();
}
