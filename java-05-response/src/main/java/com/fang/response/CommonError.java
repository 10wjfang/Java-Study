package com.fang.response;

/**
 * 错误接口
 *
 * @author fwj
 * @date 2019-01-10 22:15
 **/
public interface CommonError {
    int getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
