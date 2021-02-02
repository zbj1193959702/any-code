package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadA extends Thread {

    private Service service;

    public ThreadA(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println(String.format("线程 %s 启动了", Thread.currentThread().getName()));
        service.testMethod();
    }
}
