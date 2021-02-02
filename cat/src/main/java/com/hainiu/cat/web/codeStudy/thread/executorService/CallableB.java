package com.hainiu.cat.web.codeStudy.thread.executorService;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/24
 */
public class CallableB implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("CallableB start");

        for (int i = 0; i < 12345; i++) {
        }
        System.out.println("CallableB end");
//
//        if (1 == 1) {
//            System.out.println("1 == 1");
//            throw new Exception("CallableB exception");
//        }
        return "CallableB";
    }
}
