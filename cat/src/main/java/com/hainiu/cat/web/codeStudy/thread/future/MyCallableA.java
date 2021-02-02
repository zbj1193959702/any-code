package com.hainiu.cat.web.codeStudy.thread.future;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/19
 */
public class MyCallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableA start " + System.currentTimeMillis());

        Thread.sleep(6000);
        System.out.println("MyCallableA end " + System.currentTimeMillis());
        return "MyCallableA";
    }
}
