package com.hainiu.cat.web.codeStudy.thread.executor;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyRunnableX implements Runnable {
    private String username;

    public MyRunnableX(String username) {
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
        System.out.println(username + "run");
        try {
            Thread.sleep(2000);
        }catch (Exception e) {

        }
    }
}
