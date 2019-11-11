package com.fang;

import com.fang.impl.HelloService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IHelloService helloService = new HelloService();
        Registry registry = new Registry();
        registry.publish(helloService, 9999);
    }
}
