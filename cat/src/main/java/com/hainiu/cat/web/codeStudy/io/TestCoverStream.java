package com.hainiu.cat.web.codeStudy.io;

import java.io.*;

/**
 * create by biji.zhao on 2021/1/7
 */
public class TestCoverStream {

    public static void main(String[] args) throws Exception{
//        test_buffReader();
        testCover();
        /**
         *
         * 字节流
         *      节点流
         *      处理流
         *
         * io流体系结构中设计模式
         * 1、装饰器模式
         * 2、适配器模式
         */
        ByteArrayOutputStream baos;
        ByteArrayInputStream bais;

        CharArrayReader car;
        CharArrayWriter caw;

        //

    }

    private static void test_buffReader() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\photo\\aa_biji.txt"));
        // BufferedWriter bufferedWriter =  bufferedWriter = new BufferedWriter(new FileWriter("D:\\photo\\bb_biji.txt"));
        PrintWriter pw = new PrintWriter("D:\\photo\\bb_biji.txt");
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            /**
             * readLine  原理
             * stringBuilding  连续读，读到换行符时结束这次读写
             */
            pw.println(line);
            // 新启动一行，因为操作系统不同，换行符不同
    //       bufferedWriter.newLine();
        }

        bufferedReader.close();
        pw.close();
    }

    public static void testCover() throws Exception{

        Reader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);

        PrintWriter writer = new PrintWriter(System.out);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            writer.println(line);
            writer.flush();
        }

        bufferedReader.close();
        writer.close();
    }
}
