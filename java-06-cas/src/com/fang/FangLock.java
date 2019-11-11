package com.fang;

import sun.misc.Unsafe;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 自己实现锁
 *
 * @author fwj
 * @date 2019-03-14 13:48
 **/
public class FangLock implements Lock {
    // 保存当前线程
    private AtomicReference<Thread> threadReference = new AtomicReference<>(null);
    // 等待队列
    private LinkedBlockingQueue<Thread> waiter = new LinkedBlockingQueue<>();
    @Override
    public void lock() {
        while (!threadReference.compareAndSet(null, Thread.currentThread())) {
            waiter.add(Thread.currentThread());
            LockSupport.park();
            waiter.remove(Thread.currentThread());
        }
    }

    @Override
    public void unlock() {
        if (threadReference.compareAndSet(Thread.currentThread(), null)) {
            for (Thread thread : waiter) {
                LockSupport.unpark(thread);
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
