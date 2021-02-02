package com.hainiu.cat.web.codeStudy.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ServiceTwo {

    // 构造函数的参数是许可同一时间内 最多多少个线程可以执行 acquire 与 release 之间的代码
    private Semaphore semaphore = new Semaphore(10);

    public void testMethod() {
        try {
            semaphore.acquire(2); // 使用指定个数许可 减法操作
            System.out.println(Thread.currentThread().getName() + " begin time = "+ System.currentTimeMillis());

            int sleepValue = (int) (Math.random() * 1000);
            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName() + " 停止了 "+ sleepValue + " 秒");

            System.out.println(Thread.currentThread().getName() + " end time = "+ System.currentTimeMillis());

            semaphore.release();
        }catch (Exception e) {

        }

    }
}
