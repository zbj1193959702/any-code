package com.hainiu.cat.web.codeStudy.thread.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadB extends Thread{

    private Exchanger<String> exchanger;

    public ThreadB(Exchanger<String> exchanger) {
        super();
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            // 没有其他线程来获取就会抛出异常
//            exchanger.exchange("中国人", 1, TimeUnit.SECONDS);

            System.out.println(String.format("在线程B中得到线程A的值：%s", exchanger.exchange("中国人B")));
            System.out.println("B end !");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
