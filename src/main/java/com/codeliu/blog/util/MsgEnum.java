package com.codeliu.blog.util;

/**
 * The enum of return code and prompt information.
 */
public enum MsgEnum {

    SUCCESS(200, "success"),
    FAILD(-1, "fail"),
    PARAM_ERROR(-2, "param error"),
    PASS_ERROR(-3, "error password"),
    USER_ERROR(-4, "user does not exist"),
    USER_INVALID(-5, "the user is invalid")
    ;

    private Integer code;
    private String msg;

    private MsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
