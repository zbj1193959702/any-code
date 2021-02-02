package com.hainiu.cat.web.codeStudy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * create by biji.zhao on 2021/1/11
 */
public class OkHttpRequestClient {
    private static Gson gson = new GsonBuilder().create(); //null bean转json null值不输出
    private String host="http://47.107.60.192:8081/restcloud"; //服务器地址
    private String loginUrl = host+"/rest/core/auth/login"; //登录地址无需修改
    private final MediaType HttpJSONMediaType = MediaType.parse("application/json; charset=utf-8");
    public String token=""; //登录成功后的token
    private Date tokenCreatTime=null; //token的生成时间，超过一定时后用来重新刷新token
    private int tokenExpiresNum=24; //24小时后重新刷新token
    private String userId="elvtou"; //在API平台中注册的帐号
    private String password="elvtou@123"; //帐号的密码,,可用md5进行加密一次

    public static void main(String[] args) {
       try {
           throw new NullPointerException("xsaxascdca null");
       }catch (Exception e) {
           StringWriter sw  =   new  StringWriter();
           PrintWriter pw  =   new  PrintWriter(sw, true);
           e.printStackTrace(pw);
           String stackTraceString  =  sw.getBuffer().toString();
           System.out.println(stackTraceString);
       }
    }

    public OkHttpRequestClient() {
        this.getToken(this.userId,this.password); //登录
    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }

    public String get(String url) throws Exception {
        Request request = new Request.Builder().addHeader("identitytoken", this.token).url(url).build();
        Response response = getOkHttpClient().newCall(request).execute();
        return response.body().string();
    }

    public String post(String url, HashMap<String,String> postParamsMap) throws Exception {

        //使用键值对的方式提交
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : postParamsMap.keySet()) {
            builder.add(key, postParamsMap.get(key));//追加表单信息
        }
        RequestBody formBody = builder.build();
        Request request = new Request.Builder().addHeader("identitytoken", this.token).url(url).post(formBody).build();
        Response response = getOkHttpClient().newCall(request).execute();
        return response.body().string();
    }

    public void getToken(String userId,String password){
        if(!this.token.equals("")) {
            //如果已经登录不再登录,要看token是否已经过期,如果已过期需要重新获取
            Date nowDate=new java.util.Date();
            Calendar specialDate = Calendar.getInstance();
            specialDate.setTime(nowDate);
            specialDate.add(Calendar.HOUR, -tokenExpiresNum); //当前时间往前推指 定的小时数
            Long between = (specialDate.getTime().getTime() - this.tokenCreatTime.getTime());
            if(between<0) { //如果往前推的时间小于token创建时间则在有效期内
                return;
            }
        }
        String tokenJson="";
        try {
            //使用键值对提交的方式
            Map<String,String> postParamsMap=new HashMap<String,String>();
            postParamsMap.put("userName", userId);
            postParamsMap.put("password", password);
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : postParamsMap.keySet()) {
                builder.add(key, postParamsMap.get(key));//追加表单信息
            }
            RequestBody formBody = builder.build();
            Request request = new Request.Builder().url(this.loginUrl).post(formBody).build();
            Response response = getOkHttpClient().newCall(request).execute();
            tokenJson=response.body().string(); //登录成功返回token
            HashMap<String, String> tokenMap = gson.fromJson(tokenJson, new TypeToken<HashMap<String, String>>() {}.getType());
            String state=tokenMap.get("state");
            if(state!=null && state.equals("false")) {
                System.out.println("系统登录失败"+tokenJson);
            }else {
                this.token=tokenMap.get("identitytoken");
                this.tokenCreatTime=new java.util.Date();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
