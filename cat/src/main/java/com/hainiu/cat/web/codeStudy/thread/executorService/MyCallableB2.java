package com.hainiu.cat.web.codeStudy.thread.executorService;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/22
 */
public class MyCallableB2 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB2 begin " + System.currentTimeMillis());

        for (int i = 0; i < 123456; i++) {

            System.out.println("MyCallableB2 " + (i+1));
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("********抛出异常中断了******");
                throw new InterruptedException();
            }
        }

        System.out.println("MyCallableB2 end " + System.currentTimeMillis());
        return "ReturnB2";
    }
}
