package com.fang;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * CompletableFuture测试
 *
 * @author fwj
 * @date 2018-12-12 16:28
 **/
public class CompletableFutureTest {

    private static Random random = new Random();
    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(new Shop(), new Shop(), new Shop(), new Shop(), new Shop(),
                new Shop(), new Shop(), new Shop(), new Shop());
        long start = System.currentTimeMillis();
        //Future<Double> future = getPriceAsync();
        //Future<Double> future2 = getPriceAsync2();
        List<String> prices = findPricesAsync(shops);
//        try {
//            System.out.println("future = " + future.get() + future2.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        System.out.println("took = [" + (System.currentTimeMillis() - start) + "]");
        prices.forEach(System.out::println);
    }

    /**
     * 并行
     * @param shops
     * @return
     */
    private static List<String> findPrices(List<Shop> shops) {
        return shops.parallelStream()
                .map(shop -> String.format("price is %.2f, thread: %s", shop.getPrice(), Thread.currentThread().getName()))
                .collect(Collectors.toList());
    }

    /**
     * 异步
     * @param shops
     * @return
     */
    private static List<String> findPricesAsync(List<Shop> shops) {
        ExecutorService executorService = new ThreadPoolExecutor(shops.size(), shops.size(),
                1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(5), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                // 使用守护进程，这种方式不会阻止程序的关停
                t.setDaemon(true);
                return t;
            }
        });
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("price is %.2f, thread: %s",
                                shop.getPrice(), Thread.currentThread().getName()), executorService))
                .collect(Collectors.toList());
        // 等待所有异步操作结束
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 创建CompletableFuture对象方式
     * @return
     */
    private static Future<Double> getPriceAsync() {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice();
            future.complete(price);
        }).start();
        return future;
    }

    /**
     * 使用工厂方法
     * @return
     */
    private static Future<Double> getPriceAsync2() {
        return CompletableFuture.supplyAsync(() -> calculatePrice());
    }

    public static double calculatePrice() {
        delay(1000L);
        return random.nextDouble();
    }

    private static void delay(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
