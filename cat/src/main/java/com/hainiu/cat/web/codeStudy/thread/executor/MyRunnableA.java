package com.hainiu.cat.web.codeStudy.thread.executor;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyRunnableA implements Runnable {
    private String username;

    public MyRunnableA(String username) {
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("线程%s任务完成", this.username));
    }
}
