package com.codeliu.blog.util;

import java.util.HashMap;

public class ResultUtils {
    private Integer status;
    private String msg;
    private HashMap<String, Object> data;

    public ResultUtils() {}

    public ResultUtils isOk() {
        ResultUtils res = new ResultUtils();
        res.setStatus(0);
        res.setMsg("success");
        return res;
    }

    public ResultUtils isFail() {
        ResultUtils res = new ResultUtils();
        res.setStatus(-1);
        res.setMsg("fail");
        return res;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
