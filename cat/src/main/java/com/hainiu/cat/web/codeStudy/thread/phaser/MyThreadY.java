package com.hainiu.cat.web.codeStudy.thread.phaser;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyThreadY extends Thread {

    private MyServicePlus myServicePlus;

    public MyThreadY(MyServicePlus myServicePlus) {
        this.myServicePlus = myServicePlus;
    }

    @Override
    public void run() {
        myServicePlus.testMethodA();
    }
}
