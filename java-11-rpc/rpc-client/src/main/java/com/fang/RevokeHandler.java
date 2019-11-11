package com.fang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * 动态代理
 *
 * @author fwj
 * @date 2019-11-11 15:18
 **/
public class RevokeHandler implements InvocationHandler {
    private String address;
    private int port;

    public RevokeHandler(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public <T> T createProxyInstance(Class<?> service) {
        return (T)Proxy.newProxyInstance(service.getClassLoader(),
                new Class[]{service}, this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequst rpcRequst = new RpcRequst();
        rpcRequst.setClassName(method.getDeclaringClass().getName());
        rpcRequst.setMethodName(method.getName());
        rpcRequst.setParameterTypes(method.getParameterTypes());
        rpcRequst.setArguments(args);
        RpcClent clent = new RpcClent(address, port);
        return clent.send(rpcRequst);
    }
}
