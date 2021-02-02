package com.hainiu.cat.web.codeStudy.io;

import java.io.*;

/**
 * create by biji.zhao on 2021/1/7
 *
 * 问题：
 *  数据类型，希望将各种数据类型方便的写入文件并读取出来
 *
 *  dataInputStream
 *  dataOutputStream
 *
 *  objectInputStream
 *  objectOutputStream
 */
public class DataRunTest {

    public static void main(String[] args) {
//        write();
//        reader();
    }

    public static void reader() {
        DataInputStream dataInputStream = null;
        try {
            FileInputStream inputStream = new FileInputStream("D:\\photo\\aa_biji.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            dataInputStream = new DataInputStream(bufferedInputStream);
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readDouble());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readUTF());

            // eof  已经到达文件的末尾 end of file
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write() {
        DataOutputStream dataOutputStream = null;
        try {
            FileOutputStream outputStream = new FileOutputStream("D:\\photo\\aa_biji.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            dataOutputStream = new DataOutputStream(bufferedOutputStream);
            dataOutputStream.writeInt(10);
            dataOutputStream.writeDouble(3.14);
            dataOutputStream.writeChar('A');
            dataOutputStream.writeUTF("驴妈妈科技园区");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
