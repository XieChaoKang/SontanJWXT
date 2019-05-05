package Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @className BaiduApi
 * @Description 百度api文字识别
 * @Author Lin
 * @Date 2019/2/21 13:41
 * @Version 1.0
 * @Project WX
 **/

public class BaiduApi {

    public static final String APP_ID = "16056875";
    public static final String API_KEY = "9Od2heexLPRvgGx4Mihl38ks";
    public static final String SECRET_KEY = "F8F0aStUKdRVtDO6IlntFV3A2sVzmYEY";


    public static String  BaiDuWorld(String url){
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);


        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        System.out.println(url);


        // 调用接口
        //String path = "C:\\Users\\Lin\\Desktop\\1.png";
        org.json.JSONObject res = client.generalUrl(url, new HashMap<String,String>());

        for(int i=0;i<res.length();i++){
            System.out.println(res.toString(i));
        }

        JSONObject jsonObject = JSONObject.parseObject(res.toString(2));

        StringBuilder sb  = new StringBuilder();

        Iterator<Object> it = jsonObject.getJSONArray("words_result").iterator();
        while(it.hasNext()){
            JSONObject object = (JSONObject) it.next();
            sb.append(object.getString("words"));
        }

        return  sb.toString();
    }


    public static String  BaiDuWorld(byte []bytes){
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);


        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);



        // 调用接口
        //String path = "C:\\Users\\Lin\\Desktop\\1.png";
        org.json.JSONObject res = client.general(bytes, new HashMap<String,String>());

        for(int i=0;i<res.length();i++){
            System.out.println(res.toString(i));
        }

        JSONObject jsonObject = JSONObject.parseObject(res.toString(2));

        StringBuilder sb  = new StringBuilder();

        Iterator<Object> it = jsonObject.getJSONArray("words_result").iterator();
        while(it.hasNext()){
            JSONObject object = (JSONObject) it.next();
            sb.append(object.getString("words"));
        }

        return  sb.toString();
    }
}

