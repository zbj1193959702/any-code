package com.hainiu.cat.web.codeStudy.io;

import java.io.*;
import java.util.Scanner;

/**
 * create by biji.zhao on 2021/1/7
 */
public class OtherStreamTest {

    public static void main(String[] args) throws Exception{
        // 打印流只有输出流，没用输入流，都是处理流，不是节点流
        /**
         * 可以将任何类型的数据写入文件，但是读出来，都是string的可读数据
         */
        PrintStream ps  = new PrintStream("D:\\photo\\aa_biji.txt");
        ps.println("java jiji");

        InputStream is = new FileInputStream("D:\\photo\\aa_biji.txt");

        /**
         * 如果是Scanner scanner1 = new Scanner(System.in);
         * 读的数据是来自于控制台，但是如果是自定义的PrintStream（如：ps.println("java jiji")）就可以输出到文件
         *
         *
         *  如果是 System.out.println(next);
         *  输入的数据来源于控制台
         *  如果是自定义的InputStream  就可以指定读取的数据文件
         *
         */

        PrintStream err = System.err;
        System.err.println("我是错误提示");

        Scanner scanner = new Scanner(is);
        String next = scanner.nextLine();
        System.out.println(next);

    }
}
