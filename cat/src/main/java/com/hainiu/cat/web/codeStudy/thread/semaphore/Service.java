package com.hainiu.cat.web.codeStudy.thread.semaphore;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by biji.zhao on 2020/12/16
 */
public class Service {

    // 构造函数的参数是许可同一时间内 最多多少个线程可以执行 acquire 与 release 之间的代码
    private Semaphore semaphore = new Semaphore(3);

    private ReentrantLock lock = new ReentrantLock();

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println(String.format("线程 %s 准备", Thread.currentThread().getName()));

            lock.lock();
            System.out.println(String.format("begin hello %s", System.currentTimeMillis()));

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }

            System.out.println(String.format("end hello %s", System.currentTimeMillis()));

            lock.unlock();
            semaphore.release();
            System.out.println(String.format("线程 %s 结束", System.currentTimeMillis()));

        }catch (Exception e) {
            e.printStackTrace();
        }
        // 尝试获取锁
//        try {
//            if (semaphore.tryAcquire(3, TimeUnit.SECONDS)) {
//                System.out.println(String.format("线程 %s 进入", Thread.currentThread().getName()));
//
//                for (int i = 0; i < Integer.MAX_VALUE / 1000; i++) {
//                    String s = new String();
//                    Math.random();
//                }
//
//                semaphore.release(3);
//            }else {
//                System.out.println(String.format("线程 %s 未能进入", Thread.currentThread().getName()));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            semaphore.acquire(); // 使用一个许可 减法操作
//            System.out.println(Thread.currentThread().getName() + "begin time ="+ System.currentTimeMillis());
//
//            Thread.sleep(5000);
//
//            System.out.println(Thread.currentThread().getName() + "end time ="+ System.currentTimeMillis());
//
//            semaphore.release();
//        }catch (Exception e) {
//
//        }

    }
}
