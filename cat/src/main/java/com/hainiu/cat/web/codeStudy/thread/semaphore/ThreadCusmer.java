package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadCusmer extends Thread {

    private RepastService repastService;

    public ThreadCusmer(RepastService repastService) {
        this.repastService = repastService;
    }

    @Override
    public void run() {
        repastService.get();
    }
}
