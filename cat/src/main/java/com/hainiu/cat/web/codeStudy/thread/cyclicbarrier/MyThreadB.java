package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyThreadB extends Thread {

    private ResetService resetService;

    public MyThreadB(ResetService resetService) {
        this.resetService = resetService;
    }

    @Override
    public void run() {
        resetService.testMethod();
    }
}
