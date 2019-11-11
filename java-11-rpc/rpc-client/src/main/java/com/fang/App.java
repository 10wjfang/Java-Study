package com.fang;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RevokeHandler revokeHandler = new RevokeHandler("127.0.0.1", 9999);
        IHelloService helloService = revokeHandler.createProxyInstance(IHelloService.class);
        System.out.println(helloService.sayHello("Fang"));
    }
}
