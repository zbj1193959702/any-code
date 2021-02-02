package com.hainiu.cat.web.codeStudy.thread.executorService;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/22
 */
public class MyCallableB3 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB begin " + System.currentTimeMillis());

        for (int i = 0; i < 50; i++) {
            System.out.println("MyCallableB " + (i+1));
        }
        try {
            if (1 == 1) {
                System.out.println("null 异常");
                // 因为其比较慢，在另外的线程任务执行完成后，如果不显示的捕获异常，控制台并不会出现这个空指针异常
                throw new NullPointerException();
            }
        }catch (Exception e) {
            // 此处将捕获到异常信息
            e.printStackTrace();
        }

        System.out.println("MyCallableB end " + System.currentTimeMillis());
        return "ReturnB";
    }
}
