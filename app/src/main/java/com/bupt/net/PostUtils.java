package com.bupt.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/*
第一步：搭建服务端https://juejin.im/post/5b2b49d151882574b1588dd7
第二步：http需要设置安全设置
第三部: 客户端模拟请求

*/
//注意事项：https://blog.csdn.net/gengkui9897/article/details/82863966
public class PostUtils {
//    public static String MY_URL = "http://127.0.0.1:3000/posts";
    public static String MY_URL = "http://localhost:3000/posts";
    public static String PostJson()
    {
        String msg = "";
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(MY_URL).openConnection();
            //设置请求方式,请求超时信息
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type","application/json");
            //设置运行输入,输出:
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //Post方式不能缓存,需手动设置为false
            conn.setUseCaches(false);
            //我们请求的数据:
            String data = "{ \"id\":\"16\",\"title\": \"hello world\", \"author\": \"zhangchen\" }";
            //这里可以写一些请求头的东东...
            //获取输出流
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
             if (conn.getResponseCode() == 201) {//200系列都是成功，具体看服务端设置
                    // 获取响应的输入流对象  
                    InputStream is = conn.getInputStream();
                    // 创建字节输出流对象  
                    ByteArrayOutputStream message = new ByteArrayOutputStream();
                    // 定义读取的长度  
                    int len = 0;  
                    // 定义缓冲区  
                    byte buffer[] = new byte[1024];  
                    // 按照缓冲区的大小，循环读取  
                    while ((len = is.read(buffer)) != -1) {  
                        // 根据读取的长度写入到os对象中  
                        message.write(buffer, 0, len);  
                    }  
                    // 释放资源  
                    is.close();  
                    message.close();  
                    // 返回字符串  
                    msg = new String(message.toByteArray());  
                    return msg;
             }
        }catch(Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}