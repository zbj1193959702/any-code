package com.hainiu.cat.web.codeStudy.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/16
 */
public class Run {
    public static void main(String[] args) throws Exception{

//        testBase();
//        testGetPhase();
//        testOnAdvance();
//        testRegister();
//        testBulkRegister();
//        testArraviy();
//        testArrive();
//        testArriveTwo();

//        testAwaitAdvance();
//        testInterrupt();

//        testForceTermination();

//        testExecuterTime();


    }

    private static void testExecuterTime() throws InterruptedException {
        Phaser phaser = new Phaser(3);

        phaser.register();
        for (int i = 0; i < 3; i++) {
            ThreadQQ qq = new ThreadQQ(phaser);
            qq.setName("qq" + i);
            qq.start();
        }

        Thread.sleep(3000);

        phaser.arriveAndAwaitAdvance(); // 控制执行的时机
    }

    private static void testForceTermination() throws InterruptedException {
        Phaser phaser = new Phaser(3);
        ThreadQQ qq = new ThreadQQ(phaser);
        qq.setName("qq");
        qq.start();

        ThreadPP pp = new ThreadPP(phaser);
        pp.setName("pp");
        pp.start();

        Thread.sleep(2000);
        System.out.println("屏障失效");
        phaser.forceTermination(); // 使屏障失效

        System.out.println(phaser.isTerminated());
    }

    private static void testInterrupt() {
        Phaser phaser = new Phaser(2);
        ThreadCC cc = new ThreadCC(phaser);
        cc.setName("cc");
        cc.start();

        ThreadQQ qq = new ThreadQQ(phaser);
        qq.setName("qq");
        qq.start();

        ThreadPP pp = new ThreadPP(phaser);
        pp.setName("pp");
        pp.start();
//
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        cc.interrupt();
//        System.out.println("中断了cc");
    }

    private static void testAwaitAdvance() {
        Phaser phaser = new Phaser(3);

        ThreadQQ qq = new ThreadQQ(phaser);
        qq.setName("qq");
        qq.start();

        ThreadPP pp = new ThreadPP(phaser);
        pp.setName("pp");
        pp.start();

        // 线程会等到dd执行完后才执行cc
        ThreadCC cc = new ThreadCC(phaser);
        cc.setName("cc");
        cc.start();

        ThreadDD dd = new ThreadDD(phaser);
        dd.setName("dd");
        dd.start();
    }

    private static void testArriveTwo() {
        Phaser phaser = new Phaser(3);
        MyServicePlus myServicePlus = new MyServicePlus(phaser);

        MyThreadX x = new MyThreadX(myServicePlus);
        x.setName("X");
        x.start();

        MyThreadY y = new MyThreadY(myServicePlus);
        y.setName("Y");
        y.start();

        MyThreadZ z = new MyThreadZ(myServicePlus);
        z.setName("Z");
        z.start();
    }

    private static void testArrive() {
        Phaser phaser = new Phaser(2){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println(String.format("到了未通过 phase=%s, registeredParties=%s", phase, registeredParties));
                return super.onAdvance(phase, registeredParties);
            }
        };
        // phaser.getPhase() 第几道屏障
        // phaser.getRegisteredParties() 注册数
        // phaser.getArrivedParties()  到达屏障的数量
        System.out.println(String.format("phaser=%s, registeredParties=%s, arrivedParties=%s", phaser.getPhase(), phaser.getRegisteredParties(), phaser.getArrivedParties()));

        phaser.arrive(); // 使parties加1 并不在屏障处等待

        System.out.println(String.format("phaser=%s, registeredParties=%s, arrivedParties=%s", phaser.getPhase(), phaser.getRegisteredParties(), phaser.getArrivedParties()));


        System.out.println(String.format("phaser=%s, registeredParties=%s, arrivedParties=%s", phaser.getPhase(), phaser.getRegisteredParties(), phaser.getArrivedParties()));

        phaser.arrive(); // 作用phaser.getArrivedParties() + 1

        System.out.println(String.format("phaser=%s, registeredParties=%s, arrivedParties=%s", phaser.getPhase(), phaser.getRegisteredParties(), phaser.getArrivedParties()));
    }

    private static void testArraviy() {
        Phaser phaser = new Phaser(2);

        MyService myService = new MyService(phaser);

//        ThreadO o = new ThreadO(myService);
//        o.start();

        ThreadM a = new ThreadM(myService);
        a.start();

//        ThreadN b = new ThreadN(myService);
//        b.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // phaser 这东西会循环设置挡板
        System.out.println("---" + phaser.getArrivedParties());
        System.out.println("---" + phaser.getUnarrivedParties());
    }

    private static void testBulkRegister() {
        Phaser phaser = new Phaser(6);
        System.out.println(phaser.getRegisteredParties());

        phaser.bulkRegister(10);

        System.out.println(phaser.getRegisteredParties());

        phaser.bulkRegister(10);

        System.out.println(phaser.getRegisteredParties());
    }

    private static void testRegister() {
        Phaser phaser = new Phaser(6);
        System.out.println(phaser.getRegisteredParties());

        phaser.register();
        System.out.println(phaser.getRegisteredParties());

        phaser.register();
        System.out.println(phaser.getRegisteredParties());
    }

    private static void testOnAdvance() {
        Phaser phaser = new Phaser(2){
            // 通过新的屏障被调用
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("执行 onAdvance");
                return false; // 屏障无效不再等待
            }
        };
        MyService myService = new MyService(phaser);
        ThreadM a = new ThreadM(myService);
        a.setName("A");

        ThreadN b = new ThreadN(myService);
        b.setName("B");

        a.start();
        b.start();
    }

    private static void testGetPhase() {
        Phaser phaser = new Phaser(4);
        ThreadP threadP = new ThreadP(phaser);

        threadP.start();
    }

    private static void testBase() {
        PrintTools.phaser = new Phaser(3);

        ThreadA a = new ThreadA();
        a.setName("A");
        a.start();

        ThreadB b = new ThreadB();
        b.setName("B");
        b.start();

        ThreadC c = new ThreadC();
        c.setName("C");
        c.start();
    }
}
