package com.hainiu.cat.web.codeStudy.thread.executor;

import com.hainiu.cat.util.DateUtil;

import java.util.Date;
import java.util.concurrent.ThreadFactory;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyThreadFactoryB implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("高洪岩："+ DateUtil.formatDate(new Date()));

        // 线程池出现异常时，可以自定义工厂类处理线程出现的异常情况 UncaughtExceptionHandler
        thread.setUncaughtExceptionHandler((t, e) -> System.out.println(String.format("自定义处理异常启动：%s， 异常信息：%s", t.getName(), e.getMessage())));
        return thread;
    }
}
