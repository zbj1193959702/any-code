package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyThread extends Thread {

    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        System.out.println(String.format("线程： %s 启动了", Thread.currentThread().getName()));
        myService.testMethod();
    }
}
