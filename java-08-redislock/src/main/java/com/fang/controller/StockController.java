package com.fang.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存API
 *
 * @author fwj
 * @date 2019-04-24 11:25
 **/
@RestController
public class StockController {
    public static final String LOCK = "lock";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @GetMapping("/delstock")
    public String delStock() {
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(LOCK, "lock");
        if (result) {
            Integer stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                stock = stock - 1;
                System.out.println("剩余库存：" + stock);
                stringRedisTemplate.opsForValue().set("stock", stock.toString());
            } else {
                System.out.println("库存不足");
            }
            stringRedisTemplate.delete(LOCK);
        }
        return "success";
    }

    @GetMapping("/delstock2")
    public String delStock2() {
        RLock lock = redisson.getLock(LOCK);
        lock.lock();
        Integer stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
        if (stock > 0) {
            stock = stock - 1;
            System.out.println("剩余库存：" + stock);
            stringRedisTemplate.opsForValue().set("stock", stock.toString());
        } else {
            System.out.println("库存不足");
        }
        lock.unlock();
        return "success";
    }
}
