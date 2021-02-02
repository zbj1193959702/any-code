package com.hainiu.cat.web.codeStudy.thread.phaser;

/**
 * create by biji.zhao on 2020/12/17
 */
public class ThreadO extends Thread {

    private MyService myService ;

    public ThreadO(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testPrintlnParties();
    }
}
