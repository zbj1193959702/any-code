package com.hainiu.cat.web.codeStudy.thread.future;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/19
 */
public class MyCallableNumber implements Callable<String> {
    private int number;

    public MyCallableNumber() {
    }

    public MyCallableNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        Integer.parseInt("a");
        return "anystring";
    }
}
