package com.fang;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 没有使用CAS
 *
 * @author fwj
 * @date 2019-03-14 10:09
 **/
public class WithoutCasDemo {
    int i = 0;
    Lock lock = new FangLock();
    void increase() {
        lock.lock();
        i++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        WithoutCasDemo demo = new WithoutCasDemo();
        int THREAD_NUM = 20000;
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(()->{
                demo.increase();
            });
            threads[i].start();
        }
        for (int j = 0; j < THREAD_NUM; j++) {
            threads[j].join();
        }
        System.out.println("WithoutCasDemo.i = " + demo.i);
    }
}
