package com.hainiu.cat.web.codeStudy;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * create by biji.zhao on 2020/12/8
 */
public class TestFuture {

    public static void main(String[] args) {
//        // 两个线程的线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        //
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("爸：小红你去买瓶酒！");
//            try {
//                System.out.println("小红出去买酒了，女孩子跑的比较慢，估计5s后才会回来...");
//                Thread.sleep(5000);
//                return "我买回来了！";
//            } catch (InterruptedException e) {
//                System.err.println("小红路上遭遇了不测");
//                return "来世再见！";
//            }
//        }, executorService);
//
//        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
//            System.out.println("爸：小明你去买包烟！");
//            try {
//               System.out.println("小明出去买烟了，可能要3s后回来...");
//               Thread.sleep(3000);
//               return "我买回来了!";
//            } catch (InterruptedException e) {
//               System.out.println("小明路上遭遇了不测！");
//               return "这是我托人带来的口信，我已经不在了。";
//           }
//           },executorService);
//
//        //获取小红买酒结果，从小红的操作中获取结果，把结果打印
//        future.thenAccept((e)-> System.out.println("小红说："+e));
//        //获取小明买烟的结果
//        future1.thenAccept((e)-> System.out.println("小明说："+e));
//
//        System.out.println("爸：loading......");
//        System.out.println("爸:我觉得无聊甚至去了趟厕所。");
//        System.out.println("爸：loading......");
        SendSmsDTO sendSmsDTO = new SendSmsDTO();
        sendSmsDTO.setPhone("19985757132");
        sendSmsDTO.setContent("陈秋放大盘子");
        sendSmsDTO.setPrimaryChannelId(10005);
        sendSmsDTO.setSendChannel(1);

        AliYunSmsDTO aliYunSmsDTO = new AliYunSmsDTO();
        aliYunSmsDTO.setTemplateCode("SMS_206538491");

        Map<String, String> param = new HashMap<>();
        param.put("orderCode", "1231432144124");
        aliYunSmsDTO.setTemplateParam(JSON.toJSONString(param));
        sendSmsDTO.setAliYunSms(aliYunSmsDTO);

        System.out.println(JSON.toJSONString(sendSmsDTO));
        // {"aliYunSms":{"templateCode":"SMS_206538491","templateParam":"{\"orderCode\":\"1231432144124\"}"},"content":"陈秋放大盘子","phone":"19985757132","primaryChannelId":10005,"sendChannel":1}
    }
}
