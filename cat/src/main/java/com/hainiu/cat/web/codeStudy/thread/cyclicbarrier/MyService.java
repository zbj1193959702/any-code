package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyService {

    private CyclicBarrier cyclicBarrier;

    public MyService(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void beginRun() {
        try {
            System.out.println(String.format("%s %s begin 跑第一阶段 %s", Thread.currentThread().getName(), System.currentTimeMillis(), (cyclicBarrier.getNumberWaiting() + 1)));
            cyclicBarrier.await();
            System.out.println(String.format("%s %s end 跑第一阶段 %s", Thread.currentThread().getName(), System.currentTimeMillis(), (cyclicBarrier.getNumberWaiting() + 1)));

            System.out.println(String.format("%s %s begin 跑第二阶段 %s", Thread.currentThread().getName(), System.currentTimeMillis(), (cyclicBarrier.getNumberWaiting() + 1)));
            cyclicBarrier.await();
            System.out.println(String.format("%s %s end 跑第二阶段 %s", Thread.currentThread().getName(), System.currentTimeMillis(), (cyclicBarrier.getNumberWaiting() + 1)));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
