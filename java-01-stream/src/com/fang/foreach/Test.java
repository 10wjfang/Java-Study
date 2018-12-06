package com.fang.foreach;

import java.util.Random;

/**
 * foreach测试
 *
 * @author fwj
 * @date 2018-12-06 14:57
 **/
public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }
}
