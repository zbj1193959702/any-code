package com.hainiu.cat.web.codeStudy.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * create by biji.zhao on 2020/12/16
 */
public class Run {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA threadA = new ThreadA(exchanger);
        ThreadB b = new ThreadB(exchanger);
        threadA.start();
        b.start();
        System.out.println("main end !");
    }
}
