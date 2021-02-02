package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * create by biji.zhao on 2020/12/16
 */
public class MyThreadPlus extends Thread{

    private MyServicePlus myServicePlus;

    public MyThreadPlus(MyServicePlus myServicePlus) {
        this.myServicePlus = myServicePlus;
    }

    @Override
    public void run() {
        myServicePlus.testA();
    }
}
