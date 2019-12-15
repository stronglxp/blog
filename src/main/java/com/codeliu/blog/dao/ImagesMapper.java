package com.codeliu.blog.dao;

import com.codeliu.blog.entity.Images;
import org.apache.ibatis.annotations.Mapper;

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
}
