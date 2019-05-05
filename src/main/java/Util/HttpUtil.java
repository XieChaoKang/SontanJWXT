package Util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class HttpUtil {






    public static String Get(String url, Headers headers){


        try {

            OkHttpClient client = new OkHttpClient();

            Request request;


            request = new Request.Builder().url(url).get().headers(headers).build();


            Response response =client.newCall(request).execute();

            return  response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  "";
    }

    public static String Get(String url){


        try {

            OkHttpClient client = new OkHttpClient();

            Request request;

            request = new Request.Builder().url(url).get().build();


            Response response =client.newCall(request).execute();

            return  response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  "";
    }




    public static Response GetResponse(String url, Headers headers){


        try {

            OkHttpClient client = new OkHttpClient();

            Request request;


            request = new Request.Builder().url(url).get().headers(headers).build();


            Response response =client.newCall(request).execute();

            return  response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public static Response GetResponse(String url){


        try {

            OkHttpClient client = new OkHttpClient();

            Request request;

            request = new Request.Builder().url(url).get().build();


            Response response =client.newCall(request).execute();

            return  response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }


    public static String Post(String url,Headers headers,RequestBody requestBody){

        try{
            OkHttpClient client = new OkHttpClient();
            Request request;

            request = new Request.Builder().url(url).headers(headers).post(requestBody).build();

            Response response =client.newCall(request).execute();

            return response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  "";
    }



}
