package com.hainiu.cat.web.codeStudy.thread.executorService;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/24
 */
public class CallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("CallableA start");

        try {
            for (int i = 0; i < 65; i++) {
               Thread.sleep(1000);
                System.out.println("运行中");
                if (Thread.currentThread().isInterrupted()){
                    System.out.println(" a 异常");
                    throw new NullPointerException();
                }

            }
        }catch (Exception e) {
            System.out.println("捕获异常a");
            throw e;
        }

        System.out.println("CallableA end");

//        if (1 == 1) {
//            System.out.println("1 == 1");
//            throw new Exception("CallableA exception");
//        }
        return "CallableA";
    }
}
