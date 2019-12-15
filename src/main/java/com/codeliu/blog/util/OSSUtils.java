package com.codeliu.blog.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * Aliyun OSS utils
 */
public class OSSUtils {
    public static final Logger logger = LoggerFactory.getLogger(OSSUtils.class);
    private String endpoint = "oss-cn-shanghai.aliyuncs.com";
    private String accessKeyId = "LTAI4FsF6tyfcnEQwKHsTJqL";
    private String accessKeySecret = "4wq4ozF1CYuiTNeT8rBVhe9etk4m9G";
    private String bucketName = "codetiger";
    private String fileDir = "blog/article/images/";
    private OSSClient ossClient;

    public OSSUtils() {
        ossClient = (OSSClient) new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    public void init() {
        ossClient = (OSSClient) new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    public void destroy() {
        ossClient.shutdown();
    }

    /**
     * upload image
     * @param file
     * @return the name of image
     * @throws Exception
     */
    public String uploadImage(MultipartFile file) throws Exception {
        if (file.getSize() > 20 * 1024 * 1024) {
            return "the size of image can not exceed 20M";
        }

        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile(inputStream, name);
            return name;
        } catch (IOException e) {
            throw new Exception("image upload fail");
        }

    }

    /**
     * upload file to OSS server, if a file with the same name is overwritten
     * @param inputStream
     * @param fileName
     * @return
     */
    private String uploadFile(InputStream inputStream, String fileName) {
        String ret = "";

        try {
            // Create the metadata of the uploaded object
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            // upload file
            PutObjectResult putResult = ossClient.putObject(bucketName, fileDir + fileName, inputStream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Determine the contenttype of the file when the OSS service file is uploaded
     * @param filenameExtension
     * @return
     */
    private static String getContentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * get the path of image
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        logger.info("fileUrl = {}", fileUrl);
        if (!fileUrl.isEmpty()) {
            String[] split = fileUrl.split("/");
            return this.getUrl(this.fileDir + split[split.length - 1]);
        }

        return null;
    }

    /**
     * get the url of image
     * @param key
     * @return
     */
    private String getUrl(String key) {
        // the expiration date is 10 years
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // generate the url
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }

        return null;
    }
}
