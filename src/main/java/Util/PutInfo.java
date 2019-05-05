package Util;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.ResponseBody;

public class PutInfo {

    public static void putHeaders(Headers headers){

        for(int i=0;i<headers.size();i++){
            System.out.println(headers.name(i)+":"+headers.value(i));
        }
    }

    public static void putFormBody(FormBody formBody){

        for(int i=0;i<formBody.size();i++){
            System.out.println(formBody.name(i)+":"+formBody.value(i));
        }
    }
}
