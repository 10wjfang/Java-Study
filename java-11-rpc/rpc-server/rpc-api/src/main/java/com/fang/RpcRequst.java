package com.fang;

import java.io.Serializable;

/**
 * RPC请求类
 *
 * @author fwj
 * @date 2019-11-11 14:36
 **/
public class RpcRequst implements Serializable {
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] arguments;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
