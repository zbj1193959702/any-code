package com.hainiu.cat.web.codeStudy.thread.semaphore;

import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * create by biji.zhao on 2020/12/16
 */
public class RepastService {

    // 限制并发线程数  优于 synchronized

    volatile private Semaphore setSemaphore = new Semaphore(10); // 厨师
    volatile private Semaphore getSemaphore = new Semaphore(20); // 就餐者

    volatile private ReentrantLock lock = new ReentrantLock();
    volatile private Condition setCondition = lock.newCondition();
    volatile private Condition getCondition = lock.newCondition();

    volatile private Object[] producePosition = new Object[4];

    private boolean isEmpty() {
        return Stream.of(producePosition).noneMatch(Objects::nonNull);
    }

    private boolean isFull() {
        return Stream.of(producePosition).noneMatch(Objects::isNull);
    }

    public void set() {
        try {
            setSemaphore.acquire();
            lock.lock();
            while (isFull()) {
                setCondition.await();
            }
            for (int i = 0; i < producePosition.length; i++) {
                if (producePosition[i] == null) {
                    producePosition[i] = "数据" ;
                    System.out.println(String.format("%s 生产了 %s", Thread.currentThread().getName(), producePosition[i]));
                    break;
                }
            }
            getCondition.signalAll();
            lock.unlock();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            setSemaphore.release();
        }
    }

    public void get() {
        try {
            getSemaphore.acquire();
            lock.lock();
            while (isEmpty()) {
                getCondition.await();
            }
            for (int i = 0; i < producePosition.length; i++) {
                if (producePosition[i] != null) {
                    System.out.println(String.format("%s 消费了 %s", Thread.currentThread().getName(), producePosition[i]));
                    producePosition[i] = null;
                    break;
                }
            }
            setCondition.signalAll();
            lock.unlock();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            getSemaphore.release();
        }
    }
}
