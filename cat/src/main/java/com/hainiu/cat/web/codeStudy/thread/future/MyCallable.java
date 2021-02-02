package com.hainiu.cat.web.codeStudy.thread.future;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/19
 */
public class MyCallable implements Callable<String> {
    private int age;

    public MyCallable(int age) {
        this.age = age;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(8000);
        return "返回值 年龄是：" + age;
    }
}
