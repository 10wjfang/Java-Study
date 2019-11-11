package com.fang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题
 *
 * @author fwj
 * @date 2019-03-15 16:34
 **/
public class ABATest {
    public static AtomicInteger a = new AtomicInteger(1);
    public static void main(String[] args) throws InterruptedException {
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() +",初始值 = " + a);  //定义变量 a = 1
            try {
                Thread.sleep(1000);  //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }System.out.println("操作线程" + Thread.currentThread() +",CAS前初始值 = " + a);
            boolean isCASSuccess = a.compareAndSet(1,2); // CAS操作
            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
        },"主操作线程");

        Thread other = new Thread(() -> {
            a.incrementAndGet(); // a 加 1, a + 1 = 1 + 1 = 2
            System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ a);
            a.decrementAndGet(); // a 减 1, a - 1 = 2 - 1 = 1
            System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ a);
        },"干扰线程");

        main.start();
        other.start();
        main.join();
        System.out.println("a = " + a);
    }
}
