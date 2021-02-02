package com.hainiu.cat.web.codeStudy.thread.future;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/19
 */
public class MyCallableB implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB start " + System.currentTimeMillis());

        int i = 0;
        if (i == 0) {
            throw new Exception("抛出异常！！！");
        }
        Thread.sleep(6000);
        System.out.println("MyCallableB end " + System.currentTimeMillis());
        return "MyCallableB";
    }
}
