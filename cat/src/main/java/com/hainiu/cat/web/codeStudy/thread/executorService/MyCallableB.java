package com.hainiu.cat.web.codeStudy.thread.executorService;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/22
 */
public class MyCallableB implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB begin " + System.currentTimeMillis());

        for (int i = 0; i < 15; i++) {
            System.out.println("MyCallableB " + (i+1));
        }

        System.out.println("MyCallableB end " + System.currentTimeMillis());
        return "ReturnB";
    }
}
