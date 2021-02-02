package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * create by biji.zhao on 2020/12/16
 */
public class Run {

    public static void main(String[] args) {
        // 代码的作用是 有五个同行者执行了cyclicBarrier.await()方法后 才会往下执行
//        testCyclic();
//        testReset();
//        testUserAgain();

//        testBorken();

        ResetService resetService = new ResetService();

        MyThreadA a = new MyThreadA(resetService);
        a.start();

        MyThreadB b = new MyThreadB(resetService);
        b.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 为什么加上了一句sout 导致程序没有抛出异常 大概是因为在线程还没有执行到屏障的点就已经被reset了 所以在一直等待
        resetService.cyclicBarrier.reset();

    }

    private static void testBorken() {
        int parties = 4;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, () -> System.out.println("全倒了"));

        MyServicePlus myServicePlus = new MyServicePlus(cyclicBarrier);

        for (int i = 0; i < 5; i++) {
            MyThreadPlus plus = new MyThreadPlus(myServicePlus);
            plus.start();
        }
    }

    private static void testUserAgain() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        MyService myService = new MyService(cyclicBarrier);

        ThreadA threadA = new ThreadA(myService);
        threadA.setName("A");
        threadA.start();

        ThreadA threadB = new ThreadA(myService);
        threadB.setName("B");
        threadB.start();

        ThreadA threadC = new ThreadA(myService);
        threadC.setName("C");
        threadC.start();

        ThreadA threadD = new ThreadA(myService);
        threadD.setName("D");
        threadD.start();
    }

    private static void testReset() {
        CyclicBarrier barrier = new CyclicBarrier(3);

        try {
            MyPlusThread myPlusThread = new MyPlusThread(barrier);
            myPlusThread.start();
            Thread.sleep(500);
            System.out.println(barrier.getNumberWaiting());

            MyPlusThread one = new MyPlusThread(barrier);
            one.start();
            Thread.sleep(500);
            System.out.println(barrier.getNumberWaiting());

            MyPlusThread two = new MyPlusThread(barrier);
            two.start();
            Thread.sleep(500);
            System.out.println(barrier.getNumberWaiting());

            MyPlusThread three = new MyPlusThread(barrier);
            three.start();
            Thread.sleep(500);
            System.out.println(barrier.getNumberWaiting());

            /**
             * 1
             * 2
             * 0
             * 1
             * 屏障重置性
             */
        }catch (Exception e) {

        }
    }

    private static void testCyclic() {
        // 实现分批执行 凑齐两个就开始执行 重复
        CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println("全都到了"));

        for (int i = 0; i <4; i++) {
            MyThread one = new MyThread(barrier);
            one.start();
        }
    }
}
