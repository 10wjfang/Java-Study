package com.fang.service;

import com.fang.service.impl.FirstService;
import com.fang.service.impl.SecondService;
import com.fang.service.impl.ThirdService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 测试
 *
 * @author fwj
 * @date 2018-12-20 16:51
 **/
public class CompletableFutureTest {

    public static void main(String[] args) {
        List<IService> list = Arrays.asList(new FirstService(), new SecondService(), new ThirdService());
        List<CompletableFuture<?>> futures = list.stream()
                .map(service -> CompletableFuture.supplyAsync(() -> {
                    if (service instanceof ThirdService) {
                        return (ThirdService)((ThirdService) service).findListById(1);
                    }
                    return service.findById(1);
                }))
                .collect(Collectors.toList());
        futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
