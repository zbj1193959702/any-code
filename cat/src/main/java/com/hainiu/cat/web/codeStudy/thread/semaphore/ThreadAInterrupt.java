package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadAInterrupt extends Thread{
    private ServiceInterrupt service;

    public ThreadAInterrupt(ServiceInterrupt service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}
