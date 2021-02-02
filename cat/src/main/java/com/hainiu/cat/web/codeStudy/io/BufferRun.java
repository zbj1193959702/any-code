package com.hainiu.cat.web.codeStudy.io;

import java.io.*;

/**
 * create by biji.zhao on 2021/1/6
 */
public class BufferRun {

    /**
     *
     * FileInputStream管道比较低效
     * 所以用管道BufferedInputStream
     */
    public static void main(String[] args) throws Exception{
//        testBuff();
//        testReader();
        test_buffReader();
    }

    private static void test_buffReader() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("D:\\photo\\aa_biji.txt"));
            bufferedWriter = new BufferedWriter(new FileWriter("D:\\photo\\bb_biji.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                /**
                 * readLine  原理
                 * stringBuilding  连续读，读到换行符时结束这次读写
                 */

                bufferedWriter.write(line);
                // 新启动一行，因为操作系统不同，换行符不同
                bufferedWriter.newLine();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  输入输出是针对程序而言
     * 输入流              输出流
     * fileInputStream  fileOutputStream  字节流
     * fileReader        fileWrite  字符流
     *
     * 都是节点流，因为直接和数据打交道，不是处理流
     *
     */
    public static void testReader() {
        /**
         * 字节流可以读写任意类型的文件
         * 使用字符流只可以读写文本文件（使用记事本可以打开的文件）
         *
         * 使用字符流的好处：处理非英文方便使用
         *
         * 其实只有字节流，字符流的底层都是字节流
         */
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("D:\\photo\\aa_biji.txt");
            fileWriter = new FileWriter("D:\\photo\\bb_biji.txt");
//        int by ;
//        while ((by = fileReader.read()) != -1) {
//            fileWriter.write(by);
//            // 字符流是一个字符一个字符读的
//            System.out.println((char)by);
//        }

            char[] chars = new char[1024];
            int len;
            while ((len = fileReader.read(chars)) > 0) {
                fileWriter.write(chars, 0, len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
               if (fileWriter != null) {
                   fileWriter.close();
               }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用缓冲流可以提高效率
     *
     * 关闭高层流不用关闭底层流
     *
     * 缓冲流为什么可以提高效率：
     *
     *      缓冲流存在一个缓冲区域，程序读写数据的时候，
     * 缓冲区会从硬盘读取数据，一次性读取8192个字节，然后放在缓冲区，程序从缓冲区读数据，避免从硬盘直接读取数据
     * 写数据也是如此
     *
     * @throws IOException
     */
    public static void testBuff() throws IOException {
        // 数据源
        FileInputStream inputStream = new FileInputStream("D:\\photo\\origin.gz");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        // 目的地
        FileOutputStream outputStream = new FileOutputStream("D:\\photo\\target.gz");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);


        long start = System.currentTimeMillis();
        // 1826
//        int by;
//        while ((by = bufferedInputStream.read()) != -1) {
//            bufferedOutputStream.write(by);
//        }

        // =======================


        // 174
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(bytes)) > 0) {
            bufferedOutputStream.write(bytes, 0, len);
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);

        // 高层流会关闭底层流
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}
