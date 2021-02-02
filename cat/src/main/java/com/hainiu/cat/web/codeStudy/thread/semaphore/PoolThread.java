package com.hainiu.cat.web.codeStudy.thread.semaphore;

/**
 * create by biji.zhao on 2020/12/16
 */
public class PoolThread extends Thread {
    private ListPool listPool;

    public PoolThread(ListPool listPool) {
        this.listPool = listPool;
    }

    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String s = listPool.get();
            System.out.println(String.format("线程：  %s   获取的值： %s", Thread.currentThread().getName(), s));
            listPool.put(s);
        }
    }
}
