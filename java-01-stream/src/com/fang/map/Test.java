package com.fang.map;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * map测试
 *
 * @author fwj
 * @date 2018-12-06 15:00
 **/
public class Test {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1,3,2,5,6,7,4,7,2);
        List<Integer> squresList = integers.stream()
                .map(i -> i * i).collect(Collectors.toList());
        squresList.forEach(System.out::println);
    }
}
