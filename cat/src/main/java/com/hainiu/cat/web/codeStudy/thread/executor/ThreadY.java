package com.hainiu.cat.web.codeStudy.thread.executor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * create by biji.zhao on 2020/12/18
 */
public class ThreadY extends Thread {

    @Override
    public void run() {
        ThreadX threadX = new ThreadX();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, 3, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2), new ThreadPoolExecutor.CallerRunsPolicy());


        System.out.println("a begin " + Thread.currentThread().getName() + "--" + System.currentTimeMillis());
        poolExecutor.execute(threadX);
        poolExecutor.execute(threadX);

        poolExecutor.execute(threadX);
        poolExecutor.execute(threadX);
        poolExecutor.execute(threadX);
        poolExecutor.execute(threadX);
        // thread main end   CallerRunsPolicy 这个策略模式会用调用线程池的线程去执行这个任务，不推荐这么做，会导致主线程阻塞
        // ThreadY 线程调用 ThreadX 线程，规避CallerRunsPolicy 策略下 主线程阻塞的问题
        System.out.println("a end " + Thread.currentThread().getName() + "--" + System.currentTimeMillis());
    }
}
