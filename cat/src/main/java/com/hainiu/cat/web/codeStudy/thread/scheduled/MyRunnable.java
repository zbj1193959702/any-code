package com.hainiu.cat.web.codeStudy.thread.scheduled;

/**
 * create by biji.zhao on 2020/12/24
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
