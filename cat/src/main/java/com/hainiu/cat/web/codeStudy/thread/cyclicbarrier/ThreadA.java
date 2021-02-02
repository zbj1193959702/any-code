package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadA extends Thread {
    private MyService myService;

    public ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.beginRun();
    }
}
