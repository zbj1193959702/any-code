package com.hainiu.cat.web.codeStudy.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by biji.zhao on 2020/12/16
 */
public class LatchThread extends Thread {
    private CountDownLatch maxLatch;

    public LatchThread(CountDownLatch maxLatch) {
        this.maxLatch = maxLatch;
    }

    @Override
    public void run() {
        System.out.println(String.format("线程：%s 启动了", Thread.currentThread().getName()));
        try {
            Thread.sleep(2000);
            maxLatch.countDown();
            System.out.println(String.format("我是线程：%s ，后：%s", Thread.currentThread().getName(), maxLatch.getCount()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
