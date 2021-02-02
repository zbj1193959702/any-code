package com.hainiu.cat.web.codeStudy.thread.scheduled;

/**
 * create by biji.zhao on 2020/12/24
 */
public class RateRunnable implements Runnable {
    private String username;

    public RateRunnable(String username) {
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
        System.out.println(this.username);
    }
}
