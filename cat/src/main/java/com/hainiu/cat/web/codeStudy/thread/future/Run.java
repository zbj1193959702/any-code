package com.hainiu.cat.web.codeStudy.thread.future;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * create by biji.zhao on 2020/12/19
 */
public class Run {

    public static void main(String[] args) throws Exception{
//        testFutureGet();
//        testRunnableSubmit();
//        testRunnableResult();
//        testCancel();
//        testTimeOutAndInterrupted();
//        testSubmitExecption();
//        testSubmitReject();
//        testFeatureGet();
//        testCompletionService();
//        testTakeExecption();

    }

    public static void testTakeExecption() throws InterruptedException, ExecutionException {
        MyCallableA callableA = new MyCallableA();
        MyCallableB callableB = new MyCallableB();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3, 3, 4, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        CompletionService<String> executorCompletionService = new ExecutorCompletionService<>(poolExecutor);

        executorCompletionService.submit(callableA);

        executorCompletionService.submit(callableB);

        for (int i = 0; i < 2; i++) {
            // 不调用 future的get()方法，并不会出现异常
//            Future take = executorCompletionService.take();
            executorCompletionService.take().get();
        }
        System.out.println("main end");
    }

    public static void testCompletionService() throws InterruptedException, ExecutionException {
        List<SleepCallable> callables = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            callables.add(new SleepCallable("海牛" + i, 1000 * i));
        }

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        CompletionService<String> completionService = new ExecutorCompletionService<>(poolExecutor);

        for (int i = 4; i >= 0; i--) {
            completionService.submit(callables.get(i));
        }
        for (int i = 0; i < 5; i++) {
            // 因为4相对执行较久，所以其他任务都需等待4获取返回结果，效率相对较慢
            // CompletionService 为非阻塞的，谁先执行完，就可以获取到结果
            // 如果当前任务没有被执行完， 这将永远阻塞
            // completionService.take() 获取最先执行完的任务7777
            System.out.println(i + "-----" + completionService.take().get());

            // poll() 方法没有阻塞效果，如果没有执行完的对象就返回null
//            System.out.println(completionService.poll());

            // 超时则继续向下执行，如果没有超时直接往下执行
//            Future<String> poll = completionService.poll(4, TimeUnit.SECONDS);

        }
    }

    public static void testFeatureGet() throws InterruptedException, ExecutionException {
        List<SleepCallable> callables = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            callables.add(new SleepCallable("海牛" + i, 1000 * i));
        }

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        List<Future<String>> futures = Lists.newArrayList();
        for (int i = 4; i >= 0; i--) {
            futures.add(poolExecutor.submit(callables.get(i)));
        }
        for (int i = 0; i < 5; i++) {
            // 因为4相对执行较久，所以其他任务都需等待4获取返回结果，效率相对较慢
            System.out.println(futures.get(i).get());
        }
    }

    public static void testSubmitReject() throws InterruptedException, ExecutionException {
        RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> System.out.println(r.toString() + " 被拒绝");

        MyRunnable runnable = new MyRunnable();
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) executorService;
        executor.setRejectedExecutionHandler(rejectedExecutionHandler);

        Future<?> submit = executor.submit(runnable);
        executor.submit(runnable);
        executor.submit(runnable);

        executor.shutdown();
        executor.submit(runnable);

        Object o = submit.get();
    }

    public static void testSubmitExecption() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallableNumber(1));

        String s = future.get();

        System.out.println(s);
    }

    public static void testTimeOutAndInterrupted() throws InterruptedException, ExecutionException, TimeoutException {
        MyCallableOne callableOne = new MyCallableOne(12);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Future<String> future = poolExecutor.submit(callableOne);

        // 带参数的get 表示阻塞最大时间单位，超时将抛出timeout异常
        String s = future.get(5, TimeUnit.SECONDS);

        Thread.sleep(3000);

        boolean cancel = future.cancel(true);
        // 这个只是表示发出的取消执行的指令成功了，具体有没有取消取决于callable内部有没有 判断是否被中断

        System.out.println(cancel);
    }

    public static void testCancel() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(50, Integer.MAX_VALUE, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Callable<String> callable = () -> {
            try {
                Thread.sleep(2000);
            }catch (Exception e) {

            }
            return "我的年龄是20岁";
        };

        Future<String> future = poolExecutor.submit(callable);
        System.out.println(future.get());
        System.out.println(future.cancel(true) + "   " + future.isCancelled());
    }

    public static void testRunnableResult() throws InterruptedException, java.util.concurrent.ExecutionException {
        UserInfo userInfo = new UserInfo();
        MyRunnable myRunnable = new MyRunnable(userInfo);

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Future<UserInfo> future = poolExecutor.submit(myRunnable, userInfo);

        // 如果传入的是一个runnable  poolExecutor.submit 的第二个参数可以作为返回值
        userInfo = future.get();

        System.out.println(userInfo);
    }

    public static void testRunnableSubmit() throws InterruptedException, java.util.concurrent.ExecutionException {
        Runnable runnable = () -> System.out.println("runnable");
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Future<?> submit = poolExecutor.submit(runnable);

        System.out.println(submit.get());
        // 允许没有返回值和允许有返回值
        System.out.println(submit.isDone());
    }

    public static void testFutureGet() {
        try {
            MyCallable callable = new MyCallable(100);
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                    2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
            System.out.println("main a"+ System.currentTimeMillis());
            Future<String> submit = poolExecutor.submit(callable);
            System.out.println(submit.get());
            System.out.println("main a"+ System.currentTimeMillis());
        }catch (Exception e) {

        }
    }
}
