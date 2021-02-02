package com.hainiu.cat.web.codeStudy.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyService {
    private boolean isFair = true;
    // 公平与非公平锁，公平锁表示线程先启动有先获得锁的机会 但也不是百分百获得
    private Semaphore semaphore = new Semaphore(1, isFair);

    public void testMethod() {
        try {
            semaphore.acquire();

            System.out.println("thread name: " + Thread.currentThread().getName());
//            Thread.sleep(1000);
//            // 返回所有可用许可数 并清零
////            int i = semaphore.drainPermits();
////            System.out.println(i);
//            System.out.println("getQueueLength: " + semaphore.getQueueLength());
//
//            System.out.println("hasQueuedThreads: " +semaphore.hasQueuedThreads());

//            System.out.println(semaphore.availablePermits());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

}
