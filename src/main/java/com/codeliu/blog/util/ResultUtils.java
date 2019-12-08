package com.codeliu.blog.util;

import org.springframework.stereotype.Component;

/**
 * The structure of return data.
 * @param <T>
 */
@Component
public class ResultUtils<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResultUtils() {}

    /**
     * This structure will be return when successful.
     * @param data
     * @return
     */
    public ResultUtils<T> isOk(T data) {
        ResultUtils<T> result = new ResultUtils<>();
        result.code = MsgEnum.SUCCESS.getCode();
        result.msg = MsgEnum.SUCCESS.getMsg();
        result.data = data;
        return result;
    }

    /**
     * This structure will be return when failed.
     * @return
     */
    public ResultUtils<T> isFaild() {
        ResultUtils<T> result = new ResultUtils<>();
        result.code = MsgEnum.FAILD.getCode();
        result.msg = MsgEnum.FAILD.getMsg();
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
