package com.hainiu.cat.web.codeStudy.thread.scheduled;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/24
 */
public class CallableAllB implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("CallableAllB start");

       Thread.sleep(2000);
        System.out.println("CallableAllB end");

        return "CallableAllB";
    }
}
