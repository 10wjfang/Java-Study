package com.fang;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务注册类
 *
 * @author fwj
 * @date 2019-11-11 14:26
 **/
public class Registry {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void publish(Object service, int port) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(port);
            while (true) {
                Socket connect = socket.accept();
                executorService.execute(new RevokeHandler(service, connect));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
