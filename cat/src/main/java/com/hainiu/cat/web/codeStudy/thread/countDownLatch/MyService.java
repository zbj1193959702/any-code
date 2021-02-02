package com.hainiu.cat.web.codeStudy.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyService {

    // latch : 门闩
    private CountDownLatch down = new CountDownLatch(1);

    public void testMethod() {
        try {
            System.out.println(String.format("线程：%s 准备", Thread.currentThread().getName()));
            down.await(); // 阻塞
            System.out.println(String.format("线程：%s 结束", Thread.currentThread().getName()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downMethod() {
        System.out.println(String.format("线程：%s 开始", Thread.currentThread().getName()));
        down.countDown();  // 使计数为0 开始执行
    }
}
