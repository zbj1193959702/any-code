package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadB extends Thread {

    private Service service;

    public ThreadB(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}
