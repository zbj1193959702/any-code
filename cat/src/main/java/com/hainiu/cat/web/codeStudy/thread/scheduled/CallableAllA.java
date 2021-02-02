package com.hainiu.cat.web.codeStudy.thread.scheduled;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/24
 */
public class CallableAllA implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("CallableAllA start");

       Thread.sleep(1000);
        System.out.println("CallableAllA end");

        return "CallableAllA";
    }
}
