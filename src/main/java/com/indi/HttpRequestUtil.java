package com.indi;

import org.apache.commons.codec.binary.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestUtil {

    /**
     * 工具类不允许有 public 或 default 构造方法
     */
    private HttpRequestUtil(){}

    /**
     * 发送GET请求
     * @param url 目的地址
     * @param parameters 请求参数，Map类型
     * @param map
     * @return 远程响应结果
     */
    public static String sendGet(String url, Map<String, String> parameters, Map<String, String> map) { 
        String result="";
        BufferedReader in = null; //读取响应输入流
        StringBuffer sb = new StringBuffer(); //存储参数
        String params = ""; //编码之后的参数
        try {
            // 编码请求参数  
            if(parameters.size()==1){
                for(String name:parameters.keySet()){
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),  
                            "UTF-8"));
                }
                params=sb.toString();
            }else{
                for (String name : parameters.keySet()) {  
                    sb.append(name).append("=").append(  
                            java.net.URLEncoder.encode(parameters.get(name),  
                                    "UTF-8")).append("&");  
                }  
                String tempParams = sb.toString();
                if(tempParams != null && tempParams != ""){
                    params = tempParams.substring(0, tempParams.length() - 1);
                }
            }
            String fullUrl = url;
            if(params != null && params != ""){
                fullUrl = url + "?" + params;
            }
            System.out.println(fullUrl);
            // 创建URL对象  
            URL connURL = new URL(fullUrl);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
           // httpConn.setRequestProperty("Cookie", "collectionId=" + map.get("collectionId") + "; password=" + map.get("password"));
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result ;
    }


    public static void main(String[] args) {

        String s = HttpRequestUtil.sendGet("http://course.ougd.cn/mod/quiz/attempt.php?attempt=10810125&cmid=164674", new HashMap<String, String>(), null);
        System.out.println("s = " + s);


    }


}
