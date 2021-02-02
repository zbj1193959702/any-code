package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadP extends Thread {

    private RepastService repastService;

    public ThreadP(RepastService repastService) {
        this.repastService = repastService;
    }

    @Override
    public void run() {
        repastService.set();
    }
}
