package com.hainiu.cat.web.codeStudy.io;

import com.google.common.collect.Lists;
import com.hainiu.cat.util.DateUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * create by biji.zhao on 2021/1/4
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
//        testNew();
//        testCreateNewFile();
//        testMkdir();
//        testDelete();
//        testRename();
//        testBoolean();
//        testGetFileInfo();
//        getList();
//        studyOne();
//        studyTeo();
//        outFile();
//        testRead();
//        copyFile();
//        testChinaCode();
//        copy("D:\\photo\\xingkong.jpg", "D:\\photo\\xing.jpg");


        // 数据源
//        FileInputStream fileReader = new FileInputStream("D:\\photo\\aa_biji.txt");

        FileReader fileReader = new FileReader("D:\\photo\\aa_biji.txt");

        FileWriter fileWriter = new FileWriter("D:\\photo\\bb_biji.txt");

        // 目的地
//        FileOutputStream outputStream = new FileOutputStream("D:\\photo\\bb_biji.txt");

        // 如此可以增加效率 读取1kb的数据 一般是这个的倍数
        char[] bytes = new char[3];
        int length;
        while ((length = fileReader.read(bytes)) != -1) {
            fileWriter.write(bytes, 0, length);
            System.out.println(bytes);
        }

        fileReader.close();
        fileWriter.close();
    }

    private static void copy(String originFile, String target)  {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(originFile);
            outputStream = new FileOutputStream(target);
            int by;
            while ((by = inputStream.read()) != -1) {
                outputStream.write(by);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void testChinaCode() {
        // 中文存储分为两个字节  第一个字节是负数，第二个字节常见是负数，可能是正数，但是没影响，读到负数就开始解释
        // utf8是一个变长编码标准，可以以1~4个字节表示一个字符，而中文占3个字节

        String text = "nc好像";
        byte[] bytes = text.getBytes();
        System.out.println(Arrays.toString(bytes));
    }

    /**
     * copy 没有出现乱码
     * 如果使用转换为char类型的话，读到一个字节会装换为char 所以出现乱码
     */
    public static void copyFile() throws IOException {
        // 数据源
        FileInputStream inputStream = new FileInputStream("D:\\photo\\aa_biji.txt");
        // 目的地
        FileOutputStream outputStream = new FileOutputStream("D:\\photo\\bb_biji.txt");
        int by;
        while ((by = inputStream.read()) != -1) {
            // copy数据
            outputStream.write(by);
        }
        inputStream.close();
        outputStream.close();
    }

    public static void testRead() throws IOException {
        /**
         * io流的分类
         * 1、流向
         * 输入流   read  input
         * 输出流   write output
         *
         * 输入输出相对于程序而言
         *
         * 2、传输的内容：
         *  字节数据：图片，音频、视频
         *      字节输入流  InputStream
         *      字节输出流  OutputStream
         *  字符数据：本身也是字节数据，字符能看懂是字节码转码实现的
         *      字符输出流  Reader
         *      字符输入流  Writer
         *
         *      不管是字节流还是字符流 底层流动的都是字节
         */

        // append 表示追加到文件结尾

        // 字节输出的管道，只能操作字节数据 所以字符串必须转换为 bytes
        FileInputStream stream = new FileInputStream(new File("D:\\photo\\aa_biji.txt"));
        List<String> characters = Lists.newArrayList();

        int onChar;

        // 字符流底层是字节流 但是中文会乱码 所以需要字符流
        while ((onChar = stream.read()) != -1) {
            characters.add(((char) onChar) + "");
        }
        System.out.println(String.join("", characters));

        stream.close();
    }

    public static void outFile() throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("D:\\photo\\aa_biji.txt"),  true);
            String biji = "bijiTest";
            // 从程序中输出到文件 所以是输出流
            fileOutputStream.write(biji.getBytes());
        }catch (Exception e) {
           e.printStackTrace();
        }finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    public static void studyTeo() {
        File file = new File("D:\\photo");
        File[] files = file.listFiles();
        if (ArrayUtils.isEmpty(files)) {
            return;
        }
        Arrays.asList(files).forEach(e -> {
            // 相比contains 避免出现问题
            if (e.getName().endsWith("txt")){
                String[] split = StringUtils.split(e.getName(),".");
                String parent = e.getParent();
                e.renameTo(new File(String.format("%s\\%s%s.%s", parent, split[0], "_biji", split[1])));
            }
        });
    }

    public static void studyOne() {
        File file = new File("D:\\photo");
        File[] files = file.listFiles();
        Arrays.asList(files).forEach(e -> {
            // 相比contains 避免出现问题
            if (e.getName().endsWith("jpg")){
                System.out.println(e.getAbsolutePath());
            }
        });
    }

    public static void getList() {
        File file = new File("D:\\photo");

        String[] list = file.list();
        Arrays.asList(list).forEach(System.out::println);

        File[] files = file.listFiles();
        Arrays.asList(files).forEach(e -> System.out.println(e.getPath()));
    }

    public static void testGetFileInfo() {
        // java程序认为文件夹也是一种特殊的文件
        File file = new File("D:\\photo\\cc.txt");
        // 绝对路径
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);

        // 相对路径
        String path = file.getPath();
        System.out.println(path);

        // 文件夹的长度始终认为是0
        System.out.println(file.length());
        System.out.println(file.getName());

        long l = file.lastModified();
        Date date = new Date(l);
        System.out.println(DateUtil.formatDate(date, DateUtil.FORMAT_DEFAULT));

        File fileTwo = new File("bb.txt");
        System.out.println(fileTwo.getAbsolutePath());
        System.out.println(fileTwo.getPath());
        String name = fileTwo.getName();
        System.out.println(name);

        System.out.println(fileTwo.length());
    }

    public static void testBoolean() {
        File file = new File("D:\\photo\\show\\testRename");
        boolean directory = file.isDirectory();
        System.out.println(directory);


        boolean exists = file.exists();
        System.out.println(exists);

        boolean isFile = file.isFile();
        System.out.println(isFile);

        boolean isHidden = file.isHidden();
        System.out.println(isHidden);

        // 一般是判断文件的
        boolean b = file.canRead();
        System.out.println(b);
    }

    public static void testRename() {
        File file = new File("D:\\photo\\show\\test");

        boolean b = file.renameTo(new File("D:\\photo\\show\\testRename"));

        System.out.println(b);
    }

    public static void testDelete() {
        //        File file = new File("D:\\photo\\show\\test");
//        file.deleteOnExit();
//        System.out.println();

//        file.deleteOnExit();

        // 存在子文件的情况下，无法删除
        File file = new File("D:\\photo\\show");
        boolean delete = file.delete();
        System.out.println(delete);
    }

    public static void testMkdir() {
        File file = new File("D:\\photo\\test");
        boolean mkdir = file.mkdir();
        System.out.println(mkdir);

        // mkdir 只能创建单层文件夹
        File test = new File("D:\\photo\\show\\test");
        boolean testDik = test.mkdir();
        System.out.println(testDik);

        File testMkdirs = new File("D:\\photo\\show\\test");
        boolean mkdirs = test.mkdirs();
        System.out.println(mkdirs);

        // 同一项目下，文件夹和文件不能重名
    }

    public static void testCreateNewFile() throws IOException {
        File file = new File("D:\\photo\\cc.txt");

        // 没有指定路径会抛出异常，创建失败
        boolean newFile = file.createNewFile();

        System.out.println(newFile);

        // 如果没有指明创建文件的路径 是在项目路径下
        File file1 = new File("dd.txt");
        boolean newFile1 = file1.createNewFile();
    }

    public static void testNew() {
        // 三种创建文件的方式

        // 第一种
        File file = new File("D:\\photo\\b.txt");

        // 第二种
        File fileTwo = new File("D:\\photo", "b.txt");

        // 第三种
        File fileParent = new File("D:\\photo");
        File fileThree = new File(fileParent, "b.txt");
    }
}
