package com.hainiu.cat.web.codeStudy.thread.scheduled;

/**
 * create by biji.zhao on 2020/12/24
 */
public class RunnableA implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "begin");
        try {
            Thread.sleep(2000);
        }catch (Exception e) {

        }
        System.out.println(Thread.currentThread().getName() + "end");
    }
}
