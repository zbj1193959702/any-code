package com.hainiu.cat.web.codeStudy.thread.scheduled;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/25
 */
public class CancelRunnable implements Runnable {
    private String username;

    public CancelRunnable(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        System.out.println("start ... ");
        try {
            while (true){
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                System.out.println("线程名称：" + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("进入了海牛异常");
        }
        System.out.println("end ... ");
    }
}
