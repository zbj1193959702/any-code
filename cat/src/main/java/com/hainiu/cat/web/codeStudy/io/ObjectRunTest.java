package com.hainiu.cat.web.codeStudy.io;

import com.hainiu.cat.dao.model.House;

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
public class ObjectRunTest {

    public static void main(String[] args) {
//        write();
        reader();
    }

    public static void reader() {
        ObjectInputStream ois = null;
        try {
            FileInputStream inputStream = new FileInputStream("D:\\photo\\aa_biji.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            ois = new ObjectInputStream(bufferedInputStream);
            /**
             * 对象必须 序列化  static 不参与序列化   // transient: 临时的 也不参与序列化
             *
             * 修改了类的任何东西，都会导致反序列化失败
             *
             * 所以需要设置序列化uid
             */
            House o = (House)ois.readObject();
            System.out.println(o.getTitle() + o.getPrice());
            // eof  已经到达文件的末尾 end of file
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write() {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream outputStream = new FileOutputStream("D:\\photo\\aa_biji.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            oos = new ObjectOutputStream(bufferedOutputStream);
            House house = new House();
            house.setPrice(122);
            house.setTitle("驴妈妈");
            oos.writeObject(house);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
