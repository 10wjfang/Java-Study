package com.fang.impl;

import com.fang.IHelloService;

/**
 * Hello实现类
 *
 * @author fwj
 * @date 2019-11-11 14:21
 **/
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("接收到参数：" + content);
        return "Hello, " + content;
    }
}
