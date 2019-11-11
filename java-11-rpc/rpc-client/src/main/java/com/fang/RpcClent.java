package com.fang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * socker客户端
 *
 * @author fwj
 * @date 2019-11-11 15:59
 **/
public class RpcClent {
    private int port;
    private String address;

    public RpcClent(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public Object send(RpcRequst requst) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(address, port);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(requst);
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        return inputStream.readObject();
    }
}
