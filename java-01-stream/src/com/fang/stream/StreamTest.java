package com.fang.stream;

import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * 测试Stream API
 *
 * @author fwj
 * @date 2018-12-06 10:04
 **/
public class StreamTest {

    /**
     * 顺序流
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        return LongStream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * 并行流
     * @param n
     * @return
     */
    public static long paralleSum(long n) {
        return LongStream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 传统方式
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static void measureSumPref(Function<Long, Long> func, long n) {
        long start = System.nanoTime();
        long result = func.apply(n);
        System.out.println(String.format("用时：%s, 结果：%s", System.nanoTime() - start, result));
    }
    public static void main(String[] args) {
        measureSumPref(StreamTest::iterativeSum, 10000);
        measureSumPref(StreamTest::sequentialSum, 10000);
        measureSumPref(StreamTest::paralleSum, 10000);
    }
}
