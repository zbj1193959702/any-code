package com.hainiu.cat.web.codeStudy.thread.scheduled;

import com.google.common.collect.Lists;
import com.hainiu.cat.util.DateUtil;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * create by biji.zhao on 2020/12/24
 */
public class Run {

    public static void main(String[] args) {
//        testCallable();
//        testRunnableDelay();
//        testFixedRate();
//        testRemoveAndQueue();

//        testShutdownPolicy();

        CancelRunnable cancelCallable = new CancelRunnable("海牛");

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        ScheduledFuture<?> scheduledFuture = executor.schedule(cancelCallable, 0,  TimeUnit.SECONDS);

        try {
            Thread.sleep(2000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(executor.getQueue().size());
        System.out.println(scheduledFuture.cancel(true));
    }

    public static void testShutdownPolicy() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        executor.scheduleAtFixedRate(new RateRunnable("aaa"), 1, 3, TimeUnit.SECONDS);

//        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);

        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);

        executor.shutdown();

        System.out.println("执行了shutdown");
    }

    public static void testRemoveAndQueue() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        RateRunnable a = new RateRunnable("a");
        System.out.println(a.hashCode());

        RateRunnable b = new RateRunnable("b");
        System.out.println(b.hashCode());

        RateRunnable c = new RateRunnable("c");
        System.out.println(c.hashCode());

        RateRunnable d = new RateRunnable("d");
        System.out.println(d.hashCode());

        RateRunnable e = new RateRunnable("e");
        System.out.println(e.hashCode());

        RateRunnable f = new RateRunnable("f");
        System.out.println(f.hashCode());

        executor.scheduleAtFixedRate(a, 1, 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(b, 1, 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(c, 1, 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(d, 1, 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(e, 1, 2, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(f, 10, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // f被移除了队列
        executor.remove((Runnable) scheduledFuture);

        System.out.println("");

        BlockingQueue<Runnable> queue = executor.getQueue();

        Iterator<Runnable> iterator = queue.iterator();

        while (iterator.hasNext()){
            Runnable next = iterator.next();
            System.out.println("队列中的：" + next.hashCode());
        }
    }

    public static void testFixedRate() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        System.out.println(DateUtil.getTime());
        // 周期性执行，period 此参数指的是每个任务之间的间隔
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(new MyRunnable(), 1, 2, TimeUnit.SECONDS);
        System.out.println(DateUtil.getTime());
    }

    public static void testRunnableDelay() {
        List<Runnable> runnableList = Lists.newArrayList();
        runnableList.add(new RunnableA());
        runnableList.add(new RunnableB());
        System.out.println("=======start=========" + DateUtil.getTime());
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(runnableList.get(0), 0L, TimeUnit.SECONDS);
        scheduledExecutor.schedule(runnableList.get(1), 0L, TimeUnit.SECONDS);
        System.out.println("=======end=========" + DateUtil.getTime());
    }

    public static void testCallable() {
        List<Callable<String>> callableList = Lists.newArrayList();
        callableList.add(new CallableAllA());
        callableList.add(new CallableAllB());
        try {
            // 单任务执行的计划池
//            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//            ScheduledFuture<String> schedule = scheduledExecutorService.
//                    schedule(callableList.get(0), 4L, TimeUnit.SECONDS);
//            ScheduledFuture<String> scheduleB = scheduledExecutorService.
//                    schedule(callableList.get(1), 4L, TimeUnit.SECONDS);

            // 单任务执行，delay 是多任务一起消耗
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
            ScheduledFuture<String> schedule = scheduledExecutorService.
                    schedule(callableList.get(0), 4L, TimeUnit.SECONDS);
            ScheduledFuture<String> scheduleB = scheduledExecutorService.
                    schedule(callableList.get(1), 4L, TimeUnit.SECONDS);
            System.out.println("=======start=========" + DateUtil.getTime());
            String a = schedule.get();
            System.out.println(a);
            String b = scheduleB.get();
            System.out.println(b);
            System.out.println("=======start=========" + DateUtil.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
