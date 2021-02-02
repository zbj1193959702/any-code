package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyServicePlus {

    private CyclicBarrier cyclicBarrier;

    public MyServicePlus(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    private void beginRun( Integer count) {
        try {
            System.out.println(String.format("%s 到了等待", Thread.currentThread().getName()));

            if (Thread.currentThread().getName().equals("Thread-2")) {
                System.out.println("Thread-2 进来了");
                Thread.sleep(2000);

                // 一个线程异常并不影响其他线程
//                Integer.parseInt("a");

                // 会导致全部中断 抛出异常
                Thread.currentThread().interrupt();
            }
            cyclicBarrier.await();

            // 如果没有在指定时间到达屏障点，将抛出TimeoutException
//            cyclicBarrier.await(5, TimeUnit.SECONDS);

            // 到达屏障点的个数
            int numberWaiting = cyclicBarrier.getNumberWaiting();

            // 在屏障处等待的线程个数
            int parties = cyclicBarrier.getParties();


            System.out.println("都到了，开跑");

            System.out.println(String.format("%s 到达终点", Thread.currentThread().getName()));
        }catch (Exception e) {
            System.out.println(String.format("进入了异常：%s", cyclicBarrier.isBroken()));
            e.printStackTrace();
        }
    }

    public void testA() {
        for (int i = 0; i < 1; i++) {
            beginRun(i + 1);
        }
    }
}
