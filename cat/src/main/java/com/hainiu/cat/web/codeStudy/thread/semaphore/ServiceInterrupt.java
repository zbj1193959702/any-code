package com.hainiu.cat.web.codeStudy.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ServiceInterrupt {

    // 构造函数的参数是许可同一时间内 最多多少个线程可以执行 acquire 与 release 之间的代码
    private Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        try {
            semaphore.acquireUninterruptibly(); // 不允许被中断
//            semaphore.acquire(); // 使用一个许可 减法操作
            System.out.println(Thread.currentThread().getName() + "begin time ="+ System.currentTimeMillis());

            for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                String s = new String();
                Math.random();
            }

            System.out.println(Thread.currentThread().getName() + "end time ="+ System.currentTimeMillis());

            semaphore.release();
        }catch (Exception e) {
            System.out.println("进程：" + Thread.currentThread().getName() + " 进入了catch");
            e.printStackTrace();
        }

    }
}
