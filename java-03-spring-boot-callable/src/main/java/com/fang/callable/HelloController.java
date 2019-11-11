package com.fang.callable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * 同步和异步请求
 *
 * @author fwj
 * @date 2018-12-14 14:20
 **/
@RestController
public class HelloController {

    @RequestMapping("/say1")
    public HelloBean saySync(@RequestParam String name, @RequestParam Integer age) {
        return getBean(name, age);
    }

    @RequestMapping("/say2")
    public HelloBean sayAsync(@RequestParam String name, @RequestParam Integer age) {
        Callable<HelloBean> helloBeanCallable = new Callable<HelloBean>() {
            @Override
            public HelloBean call() throws Exception {
                return getBean(name, age);
            }
        };
        return getBean(name, age);
    }

    private HelloBean getBean(String name, Integer age) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HelloBean(name, age);
    }
}
