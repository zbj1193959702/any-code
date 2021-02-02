package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadBInterrupt extends Thread{
    private ServiceInterrupt service;

    public ThreadBInterrupt(ServiceInterrupt service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}
