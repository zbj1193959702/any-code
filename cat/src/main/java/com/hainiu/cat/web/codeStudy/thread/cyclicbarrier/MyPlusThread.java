package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyPlusThread extends Thread{
    private CyclicBarrier cyclicBarrier;

    public MyPlusThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
