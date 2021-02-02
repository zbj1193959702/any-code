package com.hainiu.cat.web.codeStudy.thread.phaser;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyThreadZ extends Thread {

    private MyServicePlus myServicePlus;

    public MyThreadZ(MyServicePlus myServicePlus) {
        this.myServicePlus = myServicePlus;
    }

    @Override
    public void run() {
        myServicePlus.testMethodB();
    }
}
