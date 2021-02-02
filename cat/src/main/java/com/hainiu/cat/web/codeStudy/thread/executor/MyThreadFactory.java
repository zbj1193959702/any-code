package com.hainiu.cat.web.codeStudy.thread.executor;

import com.hainiu.cat.util.DateUtil;

import java.util.Date;
import java.util.concurrent.ThreadFactory;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("高洪岩："+ DateUtil.formatDate(new Date()));
        return thread;
    }
}
