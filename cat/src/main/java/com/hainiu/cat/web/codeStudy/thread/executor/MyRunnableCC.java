package com.hainiu.cat.web.codeStudy.thread.executor;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyRunnableCC implements Runnable {
    private String username;

    public MyRunnableCC(String username) {
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
        System.out.println(String.format("线程：%s start 时间：%s", this.username, System.currentTimeMillis()));
        try {
            Thread.sleep(1000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(String.format("线程：%s end 时间：%s", this.username, System.currentTimeMillis()));
    }
}
