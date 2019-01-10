package com.fang.response;

import com.fang.response.CommonError;

/**
 * 错误结果
 *
 * @author fwj
 * @date 2019-01-10 21:25
 **/
public enum ErrorEnum implements CommonError {
    // 1开头的通用错误
    UNKNOW_ERROR(100001, "未知错误"),
    // 2开头为用户信息相关错误
    USER_NOT_EXIST(200001, "用户不存在"),
    ;
    private int code;
    private String msg;

    private ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public int getErrCode() {
        return this.code;
    }

    @Override
    public String getErrMsg() {
        return this.msg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.msg = errMsg;
        return this;
    }
}
