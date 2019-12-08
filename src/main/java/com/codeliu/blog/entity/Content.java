package com.codeliu.blog.entity;

import java.util.Date;

/**
 * Entity class corresponding to content table
 */
public class Content {
    private Integer contentId;
    private String contentName;
    private Date contentTime;
    private String contentBody;

    public Content() {}

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public Date getContentTime() {
        return contentTime;
    }

    public void setContentTime(Date contentTime) {
        this.contentTime = contentTime;
    }

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", contentName='" + contentName + '\'' +
                ", contentTime=" + contentTime +
                ", contentBody='" + contentBody + '\'' +
                '}';
    }
}
