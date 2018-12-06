package com.fang.func;

/**
 * Main测试类
 *
 * @author fwj
 * @date 2018-12-06 14:19
 **/
public class MainTest {
    public static void main(String[] args) {
        GreetingService greetingService = message -> System.out.println("message = " + message);
        GreetingService greetingService1 = message -> {
            System.out.println("message length = " + message.length());
        };
        greetingService.sayMessage("Hello");
        greetingService1.sayMessage("World");
    }
}
