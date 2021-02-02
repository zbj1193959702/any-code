package com.hainiu.cat.web.codeStudy.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadA extends Thread{

    private Exchanger<String> exchanger;

    public ThreadA(Exchanger<String> exchanger) {
        super();
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println(String.format("在线程A中得到线程B的值：%s", exchanger.exchange("中国人A")));
            System.out.println("A end !");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
