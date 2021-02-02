package com.hainiu.cat.web.codeStudy.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ResetService {

    public CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("全部到了"));

    public void testMethod() {
        try {
            System.out.println(String.format("线程 %s 达到", Thread.currentThread().getName()));
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
            System.out.println(String.format("线程 %s 异常", Thread.currentThread().getName()));
        }
    }
}
