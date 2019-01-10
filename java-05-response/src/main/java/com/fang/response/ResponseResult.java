package com.fang.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应结果类
 *
 * @author fwj
 * @date 2019-01-10 21:23
 **/
public class ResponseResult<T> {
    /**
     * success或者fail
     */
    private String status;
    private T data;

    private ResponseResult(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功返回
     * @param data
     * @return
     */
    public static <T> ResponseResult create(T data) {
        return new ResponseResult("success", data);
    }

    public static ResponseResult create(CommonError data) {
        Map<String, Object> error = new HashMap<>(2);
        error.put("code", data.getErrCode());
        error.put("msg", data.getErrMsg());
        ResponseResult result = new ResponseResult("fail", error);
        return result;
    }
}
