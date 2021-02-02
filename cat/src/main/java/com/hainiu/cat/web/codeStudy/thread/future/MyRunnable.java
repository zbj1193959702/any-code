package com.hainiu.cat.web.codeStudy.thread.future;

/**
 * create by biji.zhao on 2020/12/19
 */
public class MyRunnable implements Runnable {
    private UserInfo userInfo;

    public MyRunnable(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public MyRunnable() {
    }

    @Override
    public void run() {
        System.out.println("running");
        if (this.userInfo != null) {
            userInfo.setUsername("hainiu");
            userInfo.setPassword("hainiu gen");
        }
    }
}
