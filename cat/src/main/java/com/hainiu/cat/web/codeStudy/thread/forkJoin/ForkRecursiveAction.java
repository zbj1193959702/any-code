package com.hainiu.cat.web.codeStudy.thread.forkJoin;

import java.util.concurrent.RecursiveAction;

/**
 * create by biji.zhao on 2020/12/30
 */
public class ForkRecursiveAction extends RecursiveAction {
    private int beginValue;
    private int endValue;

    public ForkRecursiveAction(int beginValue, int endValue) {
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName() + "----------------");
        if (endValue - beginValue > 2) {
            // 1-10
            System.out.println("执行组合：" + beginValue +"-" + endValue);
            int middleNum = (beginValue + endValue) / 2;
            // 5

            // 1, 5
            ForkRecursiveAction leftAction = new ForkRecursiveAction(beginValue, middleNum);
            // 6, 10
            ForkRecursiveAction rightAction = new ForkRecursiveAction(middleNum + 1, endValue);

            // 1, 3 和 4, 5

            // 6, 8 和 9, 10

            // 分解任务
            this.invokeAll(leftAction, rightAction);
        }else {
            System.out.println("未执行组合为：" + beginValue + "-" + endValue);
        }
    }
}
