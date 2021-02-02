package com.hainiu.cat.web.codeStudy.thread.phaser;


import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadB extends Thread {
    private Phaser phaser;

    public ThreadB(Phaser phaser) {
        this.phaser = phaser;
    }

    public ThreadB() {
    }
    @Override
    public void run() {
        PrintTools.methodA();
    }
}
