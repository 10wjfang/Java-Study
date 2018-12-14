package com.fang;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Future测试
 *
 * @author fwj
 * @date 2018-12-12 16:07
 **/
public class FutureTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService executorService = new ThreadPoolExecutor(8, 10,
                1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));

        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongCompution();
            }
        });
        executorService.shutdown();
        doSomethingElse();
        Double result = null;
        try {
            result = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("took = [" + (System.currentTimeMillis() - start) + "]");
        System.out.println("result = " + result);
    }

    private static void doSomethingElse() {
        delay(2000);
    }

    private static Double doSomeLongCompution() {
        delay(3000);
        return 0.01;
    }

    private static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
