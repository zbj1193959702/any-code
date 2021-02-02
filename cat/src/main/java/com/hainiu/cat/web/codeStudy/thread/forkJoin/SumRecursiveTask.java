package com.hainiu.cat.web.codeStudy.thread.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * create by biji.zhao on 2020/12/30
 */
public class SumRecursiveTask extends RecursiveTask<Integer> {
    private int beginPosition;
    private int endPosition;

    public SumRecursiveTask(int beginPosition, int endPosition) {
        this.beginPosition = beginPosition;
        this.endPosition = endPosition;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (endPosition - beginPosition != 0) {
            int middleValue = (endPosition + beginPosition) / 2;

            // 1, 10 进入
            // 拆分为 1-5 和 6-10 join会一直阻塞 所以往内部执行
            // 继续拆分为1-3，4-5 和 6-8，9-10
            // 1-2,3-3, 和 4-4，5-5 和 6-7，8-8和 9-9，10-10

            SumRecursiveTask leftTask = new SumRecursiveTask(beginPosition, middleValue);
            SumRecursiveTask rightTask = new SumRecursiveTask(middleValue + 1, endPosition);

            this.invokeAll(leftTask, rightTask);

            System.out.println(String.format("正常join: %s   ", (leftTask.join() + rightTask.join())) + getComputer(beginPosition, endPosition));
            return leftTask.join() + rightTask.join();
        }else {
            return endPosition;
        }
    }


    private static String getComputer(int beginValue, int endValue) {
        return String.format("组合%s-%s", beginValue, endValue);
    }
}
