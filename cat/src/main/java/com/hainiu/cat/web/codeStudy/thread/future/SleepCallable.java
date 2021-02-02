package com.hainiu.cat.web.codeStudy.thread.future;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/19
 */
public class SleepCallable implements Callable<String> {
    private String username;
    private long sleepTime;

    public SleepCallable(String username, long sleepTime) {
        this.username = username;
        this.sleepTime = sleepTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public String call() throws Exception {
        System.out.println("call :" + username);
        Thread.sleep(sleepTime);
        return "结果：" + username;
    }
}
