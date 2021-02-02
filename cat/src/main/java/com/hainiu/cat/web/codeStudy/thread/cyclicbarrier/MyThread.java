package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyThread extends Thread{

    private CyclicBarrier cyclicBarrier;

    public MyThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
       try {
           Thread.sleep((int)(Math.random() * 10000));

           System.out.println(String.format("线程 %s 到了， 等待凑齐两个继续运行", Thread.currentThread().getName()));

           cyclicBarrier.await();

           System.out.println(String.format("线程 %s ， 已经凑齐了两个，继续运行", Thread.currentThread().getName()));
       }catch (Exception e) {
           e.printStackTrace();
       }
    }
}
