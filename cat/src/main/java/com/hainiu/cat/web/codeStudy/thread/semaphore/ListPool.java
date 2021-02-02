package com.hainiu.cat.web.codeStudy.thread.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ListPool {

    private int poolMaxSize = 3;
    private int semaphorePermits = 5;
    private List<String> list = new ArrayList<>();
    private Semaphore concurrencySemaphore = new Semaphore(semaphorePermits);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public ListPool() {
        super();
        for (int i = 0; i < poolMaxSize; i++) {
            list.add("海牛" + (i + 1));
        }
    }

    public String get() {
        String getString = null;
        try {
            concurrencySemaphore.acquire();
            lock.lock();
            while (list.size() == 0) {
                condition.await();
            }
            getString = list.remove(0);
            lock.unlock();
        }catch (Exception e) {

        }
        return getString;
    }

    public void put(String stringValue) {
        lock.lock();
        list.add(stringValue);

        //  Wakes up all waiting threads.  唤醒所有等待的线程
        condition.signalAll();
        lock.unlock();
        concurrencySemaphore.release();
    }
}
