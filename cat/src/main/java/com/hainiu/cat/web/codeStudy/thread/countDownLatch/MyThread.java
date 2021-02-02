package com.hainiu.cat.web.codeStudy.thread.countDownLatch;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyThread extends Thread {

    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}
