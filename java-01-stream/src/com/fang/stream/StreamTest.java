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

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
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
        long start = System.currentTimeMillis();
        long result = func.apply(n);
        System.out.println(String.format("用时：%s, 结果：%s", System.currentTimeMillis() - start, result));
    }
    public static void main(String[] args) {
        long n = 10_000_000L;
        measureSumPref(StreamTest::iterativeSum, n);
        measureSumPref(StreamTest::sequentialSum, n);
        measureSumPref(StreamTest::paralleSum, n);
        measureSumPref(StreamTest::rangedSum, n);
    }
}
