package com.hainiu.cat.web.codeStudy.thread.countDownLatch;

import com.hainiu.cat.web.codeStudy.thread.exchanger.ThreadA;

import java.util.concurrent.CountDownLatch;

/**
 * create by biji.zhao on 2020/12/16
 */
public class Run {

    public static void main(String[] args) {
//        testBase();
//        testMoreThread();
//        testGoToInit();
        try {
            // 裁判等待所有运动员到达
            CountDownLatch comingTag = new CountDownLatch(10);
            // 等待裁判说准备，开始
            CountDownLatch waitTag = new CountDownLatch(1);
            // 等待起跑
            CountDownLatch waitRunTag = new CountDownLatch(10);
            // 起跑
            CountDownLatch beginTag = new CountDownLatch(1);
            // 等待结束
            CountDownLatch endTag = new CountDownLatch(10);

            MyPlusThread[] myPlusThreads = new MyPlusThread[10];

            for (int i = 0; i < myPlusThreads.length; i++) {
                myPlusThreads[i] = new MyPlusThread(comingTag, waitTag, waitRunTag, beginTag, endTag);
//                myPlusThreads[i].setName("tttt"+ i);
                myPlusThreads[i].start();
            }
            System.out.println("裁判等待选手到来");
            comingTag.await();

            System.out.println("全部到齐了, 等3秒");
            Thread.sleep(5000);
            waitTag.countDown();

            System.out.println("就位");
            waitRunTag.await();
            Thread.sleep(2000);

            System.out.println("开跑");

            beginTag.countDown();

            endTag.await();
            System.out.println("全部结束，统计名次");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testGoToInit() {
        MyService service = new MyService();

        MyThread[] threads = new MyThread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(service);
            threads[i].setName("thread"+ i);
            threads[i].start();
        }

        service.downMethod();
    }

    private static void testMoreThread() {
        CountDownLatch maxRuner = new CountDownLatch(10);
        LatchThread[] latchThreads = new LatchThread[Integer.parseInt("" + maxRuner.getCount())];

        System.out.println(maxRuner.getCount());
        for (int i = 0; i < latchThreads.length; i++) {
            latchThreads[i] = new LatchThread(maxRuner);
            latchThreads[i].setName("线程：" + (i +1));
            latchThreads[i].start();
        }

        try {
            maxRuner.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" 都回来了");
    }

    private static void testBase() {
        MyService service = new MyService();
        MyThread thread = new MyThread(service);

        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.downMethod();
    }
}
