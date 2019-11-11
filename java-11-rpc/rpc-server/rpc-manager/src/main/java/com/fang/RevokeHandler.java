package com.fang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 多线程处理请求
 *
 * @author fwj
 * @date 2019-11-11 14:33
 **/
public class RevokeHandler implements Runnable {
    private Object service;
    private Socket socket;

    public RevokeHandler(Object service, Socket socket) {
        this.service = service;
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            // 获取请求
            inputStream = new ObjectInputStream(socket.getInputStream());
            Object obj = inputStream.readObject();
            if (obj instanceof RpcRequst) {
                RpcRequst rpcRequst = (RpcRequst) obj;
                Object result = revoke(rpcRequst);
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object revoke(RpcRequst rpcRequst) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName(rpcRequst.getClassName());
        Method method = clazz.getMethod(rpcRequst.getMethodName(), rpcRequst.getParameterTypes());
        return method.invoke(service, rpcRequst.getArguments());
    }
}
