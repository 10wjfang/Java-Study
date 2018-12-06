package com.fang.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * List集合类Stream测试
 *
 * @author fwj
 * @date 2018-12-06 13:47
 **/
public class StreamListTest {
    private static List<Dish> menu;
    public static void init() {
        menu = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Dish dish = new Dish(String.valueOf(i), i);
            menu.add(dish);
        }
    }

    /**
     * 外部迭代
     * @return
     */
    public static List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getSize() > 500) {
                names.add(dish.getName());
            }
        }
        return names;
    }

    public static List<String> innerNames() {
        return menu.parallelStream()
                .filter(d -> d.getSize() > 500)
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    public static void measureSumPref(Supplier<List> func) {
        long start = System.nanoTime();
        List<Dish> result = func.get();
        System.out.println(String.format("用时：%s, 结果：%s", System.nanoTime() - start, result));
    }

    public static void main(String[] args) {
        init();
        measureSumPref(StreamListTest::getNames);
        measureSumPref(StreamListTest::innerNames);
    }
}
