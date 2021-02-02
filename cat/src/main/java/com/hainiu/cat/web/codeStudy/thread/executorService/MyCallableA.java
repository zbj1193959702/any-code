package com.hainiu.cat.web.codeStudy.thread.executorService;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/22
 */
public class MyCallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableA begin " + System.currentTimeMillis());

        for (int i = 0; i < 15; i++) {
            System.out.println("MyCallableA " + (i+1));
        }

        System.out.println("MyCallableA end " + System.currentTimeMillis());
        return "ReturnA";
    }
}
