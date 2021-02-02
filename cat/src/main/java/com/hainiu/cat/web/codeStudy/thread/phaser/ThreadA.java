package com.hainiu.cat.web.codeStudy.thread.phaser;


import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadA extends Thread {
    private Phaser phaser;

    public ThreadA(Phaser phaser) {
        this.phaser = phaser;
    }

    public ThreadA() {
    }

    @Override
    public void run() {
        PrintTools.methodA();
    }
}
