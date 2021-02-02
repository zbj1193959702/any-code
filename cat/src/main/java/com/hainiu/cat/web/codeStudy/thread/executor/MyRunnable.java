package com.hainiu.cat.web.codeStudy.thread.executor;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyRunnable implements Runnable {
    private String username;

    public MyRunnable(String username) {
        this.username = username;
    }

    public MyRunnable() {
    }


    @Override
    public void run() {
        System.out.println(String.format("线程：%s开始，name：%s, 时间：%s, this：%s", Thread.currentThread().getName(),username, System.currentTimeMillis(), this));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(String.format("线程：%s结束，name：%s, 时间：%s, this：%s", Thread.currentThread().getName(),username, System.currentTimeMillis(), this));
    }
}
