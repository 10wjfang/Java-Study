package com.fang.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常返回
 *
 * @author fwj
 * @date 2019-01-10 22:51
 **/
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseResult handleException(HttpServletRequest request, Throwable ex) {
        if (ex instanceof BusinessException) {
            return ResponseResult.create((BusinessException)ex);
        }
        else {
            return ResponseResult.create(ErrorEnum.UNKNOW_ERROR.setErrMsg(ex.getMessage()));
        }
    }
}
