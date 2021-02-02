package com.hainiu.cat.web.codeStudy.thread.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * create by biji.zhao on 2020/12/30
 */
public class StringRecursiveTask extends RecursiveTask<String> {
    private int beginValue;
    private int endValue;

    public StringRecursiveTask(int beginValue, int endValue) {
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    @Override
    protected String compute() {
        if (endValue - beginValue > 2) {
            int middleValue = (endValue + beginValue) / 2;

            // 1, 20 进入
            // 拆分为 1-10 和 11-20 join会一直阻塞 所以往内部执行
            // 继续拆分为 1-5， 6-10 和 11-15，16-20
            // 继续拆分为1-3，4-5 和 6-8，9-10 和 11-13，14-15和 16-18，19-20
            StringRecursiveTask leftTask = new StringRecursiveTask(beginValue, middleValue);
            StringRecursiveTask rightTask = new StringRecursiveTask(middleValue + 1, endValue);

            this.invokeAll(leftTask, rightTask);

            System.out.println(String.format("正常join: %s   ", (leftTask.join() + rightTask.join())) + getComputer(beginValue, endValue));
            return leftTask.join() + rightTask.join();
        }else {
            String returnString = "";
            for (int i = beginValue; i <= endValue; i++) {
                returnString += i;
            }
            System.out.println(String.format("else 返回: %s  ", returnString) +  getComputer(beginValue, endValue));
            return returnString;
        }
    }

    private static String getComputer(int beginValue, int endValue) {
        return String.format("组合%s-%s", beginValue, endValue);
    }

}
