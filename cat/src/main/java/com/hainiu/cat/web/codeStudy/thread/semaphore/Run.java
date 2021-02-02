package com.hainiu.cat.web.codeStudy.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class Run {

    public static void main(String[] args) {
//        testOneLimit();
//        multpileThread();
//        testReleaseMore();
//        testInterrupt();
//
//        testQueue();

//        testFair();

//        testTryAcquire();

        // 多进路-多处理-多出路实验
//        testMoreThread();
//        testPool();

        try {
            RepastService repastService = new RepastService();
            ThreadP[] ps = new ThreadP[60];
            ThreadCusmer[] cs = new ThreadCusmer[60];

            for (int i = 0; i < 60; i++) {
                ps[i] = new ThreadP(repastService);
                cs[i] = new ThreadCusmer(repastService);
            }

            Thread.sleep(2000);

            for (int i = 0; i < 60; i++) {
                ps[i].start();
                cs[i].start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void testPool() {
        ListPool listPool = new ListPool();

        PoolThread[] poolThreads = new PoolThread[12];

        for (int i = 0; i < poolThreads.length; i++) {
            poolThreads[i] = new PoolThread(listPool);
            poolThreads[i].setName(i+"");
            poolThreads[i].start();
        }
    }

    private static void testMoreThread() {
        Service service = new Service();
        ThreadA[] threadAS = new ThreadA[12];
        for (ThreadA threadA : threadAS) {
            ThreadA threadA1 = new ThreadA(service);
            threadA1.start();
        }
    }

    private static void testTryAcquire() {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("a");
        threadA.start();

        ThreadB b = new ThreadB(service);
        b.setName("b");
        b.start();
    }

    private static void testFair() {
        MyService myService = new MyService();
        MyThread o = new MyThread(myService);
        o.start();

        MyThread[] myThreads = new MyThread[4];
        for (MyThread myThread : myThreads) {
            myThread = new MyThread(myService);
            myThread.start();
        }
    }

    private static void testQueue() {
        MyService myService = new MyService();
//        myService.testMethod();

        MyThread[] myThreads = new MyThread[4];
        for (MyThread myThread : myThreads) {
            myThread = new MyThread(myService);
            myThread.start();
        }
    }

    private static void testInterrupt() {
        try {
            ServiceInterrupt interrupt = new ServiceInterrupt();
            ThreadAInterrupt a = new ThreadAInterrupt(interrupt);
            a.setName("a");
            a.start();

            ThreadBInterrupt b = new ThreadBInterrupt(interrupt);
            b.setName("b");
            b.start();

//            Thread.sleep(1000);

            b.interrupt();
            System.out.println("main 中断了");

        }catch (Exception e) {

        }
    }

    private static void testReleaseMore() {
        try {
            Semaphore semaphore = new Semaphore(5);
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();

            System.out.println(semaphore.availablePermits());

            semaphore.release();
            semaphore.release();
            semaphore.release();
            semaphore.release();
            semaphore.release();

            System.out.println(semaphore.availablePermits());

            // 相当于动态的添加可执行数
            semaphore.release();

            System.out.println(semaphore.availablePermits());
        }catch (Exception e) {

        }
    }

    private static void multpileThread() {
        ServiceTwo serviceTwo = new ServiceTwo();
        ThreadTwoA[] threadAS = new ThreadTwoA[10];
        for (int i = 0; i < 10; i++) {
            threadAS[i] = new ThreadTwoA(serviceTwo);
            threadAS[i].start();
        }
    }

    private static void testOneLimit() {
        Service service = new Service();

        ThreadA threadA = new ThreadA(service);
        threadA.setName("a");

        ThreadB threadB = new ThreadB(service);
        threadB.setName("b");

        ThreadC threadC = new ThreadC(service);
        threadC.setName("c");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
