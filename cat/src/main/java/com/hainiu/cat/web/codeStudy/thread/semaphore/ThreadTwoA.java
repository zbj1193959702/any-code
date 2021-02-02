package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadTwoA extends Thread{
    private ServiceTwo service;

    public ThreadTwoA(ServiceTwo service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}
