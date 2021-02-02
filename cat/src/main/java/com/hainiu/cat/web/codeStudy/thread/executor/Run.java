package com.hainiu.cat.web.codeStudy.thread.executor;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * create by biji.zhao on 2020/12/18
 */
public class Run {

    public static void main(String[] args) throws Exception {

        Runnable runnable = ()-> {
            System.out.println(String.format("thread %s start", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            }catch (Exception e) {

            }
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };

//        testCompletedTaskCount(runnable);

//        testActiveCount(runnable);

//        testSubmitAndRemove();
//        testBeforeAndAfterExecute();
//        testDiscardPolicy();
        //        testCallerRunsPolicyTwo();
//        testAbortPolicy();
//        testCallerRunsPolicy();

//        testSynchronousQueueCapacity();
//        testLinkQueueCapacity();
//        TestCompletedTaskCount();
//        testPrestart();
//        allowsCoreThreadTimeOut();
//        TestRejectedExecutionHandler();
        // 出现了  RejectedExecutionException 异常
//        testUncaughtExceptionHandler();
//        testThreadFactory();
//        testBase();
//        testForeachExecutor();
//        testThread();
//        testUserAgain();
//        testFactory();
//        testFixedThread();
//        testFixedFactory();
//        testSingleThread();
//        testPoolSize();
//        testQueueNoUpCoreSize();
//        testSynchronousQueueSize();
//        testPutToQueue();
//        testQueue();
//        testShutdown();
//        testShutdownNow();
//        testTerminated();
//        testAwaitTermination();
    }

    public static void testCompletedTaskCount(Runnable runnable) throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 3, 4, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(poolExecutor.getCompletedTaskCount());
        }
    }

    public static void testActiveCount(Runnable runnable) throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 3, 4, TimeUnit.SECONDS, new SynchronousQueue<>());
        poolExecutor.execute(runnable);

        System.out.println(poolExecutor.getActiveCount());
        System.out.println(poolExecutor.getCorePoolSize());


        Thread.sleep(10000);

        System.out.println(poolExecutor.getActiveCount());
        System.out.println(poolExecutor.getPoolSize());
    }

    public static void testSubmitAndRemove() throws InterruptedException {
        Runnable runnable = ()-> {
            System.out.println(String.format("thread %s start", Thread.currentThread().getName()));
            try {
                Thread.sleep(3000);
            }catch (Exception e) {

            }
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };

        Runnable runnableB = ()-> {
            System.out.println(String.format("thread %s start", Thread.currentThread().getName()));
            try {
                Thread.sleep(3000);
            }catch (Exception e) {

            }
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1, 2, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        poolExecutor.execute(runnable);
//        poolExecutor.execute(runnableB); 若未在执行可以被移除

        poolExecutor.submit(runnableB);

        Thread.sleep(1000);
        poolExecutor.remove(runnable); // 任务正在执行不可删除
        poolExecutor.remove(runnableB); // 任务不在执行可删除
    }

    public static void testBeforeAndAfterExecute() {
        MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor(
                2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        myThreadPoolExecutor.execute(new MyRunnableCC("a"));
        myThreadPoolExecutor.execute(new MyRunnableCC("b"));
    }

    public static void testDiscardPolicy() throws InterruptedException {
        /**
         * 线程池有不同的拒绝策略
         * 1：AbortPolicy: 当任务添加到线程池中被拒绝时，它将抛出RejectedExecutionException
         * 2:CallerRunsPolicy: 当任务添加到线程池中被拒绝时，会使用调用线程池的 Thread 线程对象处理被拒绝的任务
         * 3：DiscardOldestPolicy: 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中
         * 4：DiscardPolicy: 当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务
         */

        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, 3, 5, TimeUnit.SECONDS,
                queue, new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(new MyRunnableOne((i + 1)+ ""));
        }
        Thread.sleep(50);
        Iterator<Runnable> iterator = queue.iterator();
        while (iterator.hasNext()) {
            MyRunnableOne next = (MyRunnableOne)iterator.next();
            System.out.println("a：" + next.getUsername());
        }
        // DiscardPolicy 策略下任务6、7被丢弃
        // DiscardOldestPolicy 策略下，6、7替换了当时队列内最老的任务
        poolExecutor.execute(new MyRunnableOne(6+ ""));
        poolExecutor.execute(new MyRunnableOne(7+ ""));

        iterator = queue.iterator();
        while (iterator.hasNext()) {
            MyRunnableOne next = (MyRunnableOne)iterator.next();
            System.out.println("b：" + next.getUsername());
        }
    }

    public static void testCallerRunsPolicyTwo() {
        ThreadY threadY = new ThreadY();
        threadY.setName("YY");
        threadY.start();
    }

    public static void testCallerRunsPolicy() {
        Runnable runnable = ()-> {
            try {
                Thread.sleep(3000);
            }catch (Exception e) {

            }
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, 3, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2), new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("a begin " + Thread.currentThread().getName() + "--" + System.currentTimeMillis());
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);

        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable); // thread main end   CallerRunsPolicy 这个策略模式会用调用线程池的线程去执行这个任务，不推荐这么做，会导致主线程阻塞

        System.out.println("a end " + Thread.currentThread().getName() + "--" + System.currentTimeMillis());
    }

    public static void testAbortPolicy() {
        Runnable runnable = ()-> {
            try {
                Thread.sleep(3000);
            }catch (Exception e) {

            }
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, 3, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);

        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
    }

    public static void testSynchronousQueueCapacity() {
        SynchronousQueue<Runnable> queue = new SynchronousQueue<>();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, queue);
        poolExecutor.execute(new MyRunnable());
        poolExecutor.execute(new MyRunnable());
        poolExecutor.execute(new MyRunnable());

        System.out.println(queue.size() );
    }

    public static void testLinkQueueCapacity() {
        Runnable runnable = ()-> {
            System.out.println(String.format("thread %s start", Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            }catch (Exception e) {

            }
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };

        // 无参构造时 容量是 integer.MAX_VALUE
        LinkedBlockingDeque<Runnable> linkedBlockingDeque = new LinkedBlockingDeque<>(2);

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, linkedBlockingDeque);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);

        // 队列的长度放不下三个任务，因此有一个任务被拒绝执行
        poolExecutor.execute(runnable);

        System.out.println(poolExecutor.getPoolSize());
        System.out.println(poolExecutor.getQueue().size());
    }

    private static void TestCompletedTaskCount() throws InterruptedException {
        Runnable runnable = ()-> {
            try {
                Thread.sleep(1000);
            }catch (Exception e) {

            }
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        for (int i = 0; i < 7; i++) {
            poolExecutor.execute(runnable);
        }

        for (int i = 0; i < 8; i++) {
            Thread.sleep(1000);
            // 完成的task数量
            System.out.println(poolExecutor.getCompletedTaskCount());
        }
    }

    private static void testPrestart() {
        Runnable runnable = ()-> {
            System.out.println(String.format("thread %s begin", Thread.currentThread().getName()));
            try {
                Thread.sleep(4000);
            }catch (Exception e) {

            }
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

//        System.out.println("线程数A：" + poolExecutor.getPoolSize());
//        // 启动一个核心线程
//        System.out.println(poolExecutor.prestartCoreThread());
//
//        System.out.println("线程数B：" + poolExecutor.getPoolSize());
//        // 启动一个核心线程
//        System.out.println(poolExecutor.prestartCoreThread());
//
//        // 无法再启动核心线程
//        System.out.println(poolExecutor.prestartCoreThread());
//        System.out.println(poolExecutor.prestartCoreThread());
//        System.out.println(poolExecutor.prestartCoreThread());
//        System.out.println(poolExecutor.prestartCoreThread());

        System.out.println("线程数A：" + poolExecutor.getPoolSize());
        // 启动所有的核心线程
        System.out.println(poolExecutor.prestartAllCoreThreads());
    }

    private static void allowsCoreThreadTimeOut() {
        Runnable runnable = ()-> {
            System.out.println(String.format("thread %s begin", Thread.currentThread().getName()));
            System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
        };


        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 3, 4, TimeUnit.SECONDS, new SynchronousQueue<>());

        poolExecutor.allowCoreThreadTimeOut(Boolean.TRUE);

        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);

        System.out.println(poolExecutor.allowsCoreThreadTimeOut());
    }

    private static void TestRejectedExecutionHandler() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

        // 用于处理线程池未处理的线程的异常情况
        poolExecutor.setRejectedExecutionHandler((r, executor) -> System.out.println(String.format("我是不允许执行的任务：%s", ((MyRunnableOne)r).getUsername())));


        for (int i = 0; i < 4; i++) {
            MyRunnableOne runnableOne = new MyRunnableOne("线程"+ i);
            poolExecutor.execute(runnableOne);
        }
    }

    private static void testUncaughtExceptionHandler() {
        Runnable runnable = () -> {
            System.out.println(String.format("线程：%s开始， 时间：%s, ", Thread.currentThread().getName(), System.currentTimeMillis()));
           String a = null;
            int i = a.indexOf(0);
            System.out.println(String.format("线程：%s结束， 时间：%s, ", Thread.currentThread().getName(), System.currentTimeMillis()));

        };

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        poolExecutor.setThreadFactory(new MyThreadFactoryB());
        poolExecutor.execute(runnable);
    }

    private static void testThreadFactory() {
        MyRunnableOne runnableOne = new MyRunnableOne();
//
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
//                2, 100, 100, TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(), new MyThreadFactory());

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, 100, 100, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());

        poolExecutor.setThreadFactory(new MyThreadFactory()); // 可以设置线程工厂类，也可以在构造器里面指定

        poolExecutor.execute(runnableOne);
    }

    private static void testAwaitTermination() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println(String.format("线程：%s start 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            try {
//                Thread.sleep(2000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.format("线程：%s end 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
        };

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.shutdown();

        // 方法awaitTermination 是阻塞式的 表示最长等待10秒钟，看线程池是否停止 ,如果线程池停止了，返回true 继续往下执行
        boolean b = poolExecutor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println(b);

        System.out.println("main end");
    }

    private static void testTerminated() throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                // 如果超时时间为0 则为执行完任务立即删除
                2, 10, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        for (int i = 0; i < 4; i++) {
            MyRunnableA runnableA = new MyRunnableA("" + i);
            poolExecutor.execute(runnableA);
        }

        System.out.println(poolExecutor.isShutdown());

        Thread.sleep(1000);
        List<Runnable> runnables = poolExecutor.shutdownNow();

        runnables.forEach(e -> System.out.println(((MyRunnableA)e).getUsername()));

        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            System.out.println(poolExecutor.isTerminating());
            if (poolExecutor.isTerminated()) {
                System.out.println("线程池已经完全停止");
                break;
            }
        }
    }

    private static void testShutdownNow() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                    String string = new String();
                    Math.random();
                    Math.random();

                    Math.random();
                    Math.random();

                    Math.random();
                    Math.random();

//                    if (Thread.currentThread().isInterrupted()) { // 任务被取消了
//                        System.out.println("任务没有完成，就中断了");
//                        throw new InterruptedException();
//                    }
                }
                System.out.println("任务执行完成");
            }catch (Exception e) {
                System.out.println("进入了异常");
                e.printStackTrace();
            }

        };
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                // 如果超时时间为0 则为执行完任务立即删除
                2, 9999, 9999, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);

        Thread.sleep(1000);
        /**
         * 虽然中断了任务，但是在执行的任务依旧会执行下去，除非在线程内判断了当前线程是否是一个中断的线程
         */
        poolExecutor.shutdownNow();  // 后续任务不再执行

        // 这个再一次执行的时候会报错
        poolExecutor.execute(runnable);

        System.out.println("main end");
    }

    private static void testShutdown() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println(java.lang.String.format("线程：%s start 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            try {
                Thread.sleep(3000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.format("线程：%s end 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
        };
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                // 如果超时时间为0 则为执行完任务立即删除
                2, 9999, 9999, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);

        Thread.sleep(1000);
        poolExecutor.shutdown();  // 后续任务不再执行
        poolExecutor.execute(runnable); // 程序抛出异常，因为线程池被中断

        System.out.println("main end");
    }

    private static void testQueue() throws InterruptedException {
        Runnable runnable = Run::soutAndSleepOne;

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                // 如果超时时间为0 则为执行完任务立即删除
                7, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        /**
         * new LinkedBlockingDeque<>() 是一个没有上线的队列
         * 因此线程池中执行的任务大于核心进程数的时候会被放入队列，因此keepAliveTime 就没有了任何意义
         *  LinkedBlockingDeque  时 maximumPoolSize和keepAliveTime 无效 多余的任务始终会被放入队列等待执行
         *
         * SynchronousQueue 表示最大线程数和超时时间有效
         * 因此不会放入队列，会创建线程执行，在执行任务完成后，将多余线程对象移除
         *
         *
         */
        for (int i = 0; i <= 8; i++) {
            poolExecutor.execute(runnable);
        }


        System.out.println(poolExecutor.getCorePoolSize());
        System.out.println(poolExecutor.getPoolSize());
        System.out.println(poolExecutor.getQueue().size());

        Thread.sleep(10000);
        System.out.println(poolExecutor.getCorePoolSize());
        System.out.println(poolExecutor.getPoolSize());
        System.out.println(poolExecutor.getQueue().size());
    }

    private static void soutAndSleepOne() {
        System.out.println(String.format("线程：%s start 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testPutToQueue() throws InterruptedException {
        Runnable runnable = () -> {
            soutAndSleepOne();
        };
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                7, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);
        poolExecutor.execute(runnable);

        System.out.println(poolExecutor.getQueue().size());
        System.out.println(poolExecutor.getPoolSize());

        Thread.sleep(3000);

        System.out.println(poolExecutor.getQueue().size());
        System.out.println(poolExecutor.getPoolSize());

        Thread.sleep(2000);

        System.out.println(poolExecutor.getQueue().size());
        System.out.println(poolExecutor.getPoolSize());
    }

    private static void testSynchronousQueueSize() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                7, 8, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

        Runnable runnable = () -> {
            soutAndSleepOne();
        };


        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);

        Thread.sleep(1000);

        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getPoolSize());
        System.out.println(threadPoolExecutor.getQueue().size());

        Thread.sleep(10000);
        System.out.println("====================");

        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getPoolSize());
        System.out.println(threadPoolExecutor.getQueue().size());
    }

    private static void testQueueNoUpCoreSize() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                7, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Runnable runnable = () -> {
            soutAndSleepOne();
        };


        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);

        Thread.sleep(1000);

        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getPoolSize());
        System.out.println(threadPoolExecutor.getQueue().size());

        Thread.sleep(10000);
        System.out.println("====================");

        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getPoolSize());
        System.out.println(threadPoolExecutor.getQueue().size());
    }

    private static void testPoolSize() {
        /**
         * int corePoolSize:        线程池中保存的线程数，包括空闲线程，也就是核心池的大小
         * int maximumPoolSize：   线程池允许最大的线程数
         * long keepAliveTime：     如果线程池内数量大于corePoolSize 在指定时间内是不允许移除这些线程的，超时将移除
         * TimeUnit unit：           超时移除时间单位
         * BlockingQueue<Runnable> workQueue    执行前用于保存任务的队列，此队列仅仅保持由executor提交的runnable任务
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                7, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        System.out.println(threadPoolExecutor.getMaximumPoolSize());
        System.out.println(threadPoolExecutor.getCorePoolSize());

        System.out.println("\n");

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                7, 8, 5, TimeUnit.SECONDS, new SynchronousQueue<>());
        System.out.println(threadPoolExecutor.getMaximumPoolSize());
        System.out.println(threadPoolExecutor.getCorePoolSize());
    }

    private static void testSingleThread() {
        // 单一线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 3; i++) {
            executorService.execute(()-> {
                System.out.println(String.format("%s start", Thread.currentThread().getName()));
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {

                }
                System.out.println(String.format("%s end", Thread.currentThread().getName()));
            });
        }
    }

    private static void testFixedFactory() {
        MyThreadFactory myThreadFactory = new MyThreadFactory();

        ExecutorService executorService = Executors.newFixedThreadPool(3, myThreadFactory);

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                System.out.println(String.format("%s start", Thread.currentThread().getName()));
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {

                }
                System.out.println(String.format("%s end", Thread.currentThread().getName()));
            });
        }
    }

    private static void testFixedThread() {
        // 有界线程池 newFixedThreadPool 控制线程的数量
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.execute(()-> {
                System.out.println(String.format("%s start", Thread.currentThread().getName()));
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {

                }
                System.out.println(String.format("%s end", Thread.currentThread().getName()));
            });
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===========================");

        for (int i = 0; i < 4; i++) {
            executorService.execute(()-> {
                System.out.println(String.format("%s start", Thread.currentThread().getName()));
                try {
                    Thread.sleep(2000);
                }catch (Exception e) {

                }
                System.out.println(String.format("%s end", Thread.currentThread().getName()));
            });
        }
    }

    private static void testFactory() {
        MyThreadFactory myThreadFactory = new MyThreadFactory();
        ExecutorService executorService = Executors.newCachedThreadPool(myThreadFactory);
        executorService.execute(() -> System.out.println(String.format("我在运行， %s", Thread.currentThread().getName())));
    }

    private static void testUserAgain() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 4; i++) {
            MyRunnable runnable = new MyRunnable((i + 1) + "");
            executorService.execute(runnable);
        }

        // 复用线程对象 而非runnable对象

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n");

        for (int i = 0; i < 4; i++) {
            MyRunnable runnable = new MyRunnable((i + 1) + "");
            executorService.execute(runnable);
        }
    }

    private static void testThread() {
        MyRunnable runnable = new MyRunnable();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executorService.execute(runnable);
        }
    }

    private static void testForeachExecutor() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> System.out.println("run"));
        }
    }


    private static void testBase() {
        // 创建无界限线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                System.out.println("Runnable begin ");
                Thread.sleep(1000);

                System.out.println("A");

                System.out.println("Runnable end ");
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                System.out.println("Runnable 2 begin ");
                Thread.sleep(1000);

                System.out.println("A");

                System.out.println("Runnable 2 end ");
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
