package com.hainiu.cat.web.codeStudy.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyPlusThread extends Thread {

    private CountDownLatch comingTag;
    private CountDownLatch waitTag;
    private CountDownLatch waitRunTag;
    private CountDownLatch beginTag;
    private CountDownLatch endTag;

    public MyPlusThread(CountDownLatch comingTag, CountDownLatch waitTag, CountDownLatch waitRunTag, CountDownLatch beginTag, CountDownLatch endTag) {
        super();
        this.comingTag = comingTag;
        this.waitTag = waitTag;
        this.waitRunTag = waitRunTag;
        this.beginTag = beginTag;
        this.endTag = endTag;
    }

    @Override
    public void run() {
        try {
            System.out.println("运动员到达现场");

            Thread.sleep((int)(Math.random() * 10000));
            System.out.println(String.format("线程 %s 到达起跑线了", Thread.currentThread().getName()));
            comingTag.countDown();

            waitTag.await();

            Thread.sleep((int)(Math.random() * 10000));

            waitRunTag.countDown();


//            beginTag.await(3, SECONDS); // 阻塞等待指定时间 否则往下执行
            beginTag.await();

            Thread.sleep((int)(Math.random() * 10000));

            System.out.println("运动员开始跑了");
            endTag.countDown();

            System.out.println(String.format("%s 到达", Thread.currentThread().getName()));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
