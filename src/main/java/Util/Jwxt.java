package Util;

import com.baidu.aip.util.Base64Util;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pojo.User;
import pojo.UserModule;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;


public class Jwxt {

    //登录页需要提交的数据
    private  String __VIEWSTATE = "";
    private  String txtUserName = "";
    private  String TextBox2 = "";
    private  String txtSecretCode = "";
    private  String RadioButtonList1 = "学生";
    private  String Button1 = "";
    private  String lbLanguage = "";
    private  String hidPdrs = "";
    private  String hidsc = "";

    //初始化后获得COOKIES
    private  String COOKIES = "";

    //网站导航
    private  String main_url = "http://jwxt.sontan.net/Default2.aspx";
    private  String code_url = "http://jwxt.sontan.net/CheckCode.aspx?";
    private  String STUMAIN = "http://jwxt.sontan.net/xs_main.aspx?xh=";
    private  String ModuelUrl = "http://jwxt.sontan.net/";

    //登录成功之前的需要提交的协议头
    private  Headers Cookieheaders;

    //登录状态
    public  boolean loginStatus = false;

    //学生信息
    public  String stuName;

    //主页信息
    public  String MainContent = "";


    //其他页面信息
    private  UserModule userModule = null;

    //成绩页面是否已经浏览过的状态
    private  boolean scoreStatus= false;



    //1  初始化页面 获得cookies
    //2 获取隐藏域
    public  void init() {
        try {


            userModule = null;

            loginStatus = false;

            Cookieheaders = null;

            scoreStatus = false;


            MainContent = "";

            Response response = HttpUtil.GetResponse(main_url);

            String content = response.body().string();


            // 获取COOKIES
            List<String> cookies = response.headers("set-cookie");
            StringBuffer sb = new StringBuffer();
            for (String cookie : cookies) {
                cookie = cookie.substring(0, cookie.indexOf(";") + 1);
                sb.append(cookie);
            }
            COOKIES = sb.toString().substring(0, sb.toString().lastIndexOf(";"));

            //设置全局Cookies 加入Headers
            Cookieheaders = new Headers.Builder()
                    .add("Cookie", COOKIES)
                    .add("Content-Type","application/x-www-form-urlencoded")
                    .build();


            //2 获取隐藏域
            getVIEWSTATE(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void getVIEWSTATE(String content){
        Document document = Jsoup.parse(content);

        Elements element = document.select("input[name=__VIEWSTATE]");

        String result = element.attr("value");
        if(result!="") __VIEWSTATE=result;
    }









    //3 获取验证码 并且编码成BASE64返回给前端
    public  String onclickPic() {
        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(code_url).addHeader("Cookie", COOKIES)
                    .addHeader("Content-Type", "image/Gif; charset=gb2312").get().build();


            Response response = client.newCall(request).execute();

            byte[] bytes = response.body().bytes();


            String base64 = Base64Util.encode(bytes);

   /*
            //base转换成图片链接 从百度ORC解析验证码  不识别 emmm
            Headers imgHeaders = new Headers.Builder().add("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8").build();
            FormBody imgBody = new FormBody.Builder().add("bi",base64).add("ext","png").build();


            String result = HttpUtil.Post("http://coolaf.com/tool/ajaximg",imgHeaders,imgBody);
            result = result.substring(result.indexOf("data")+7,result.indexOf("png")+3);

            String imgUrl = "http://coolaf.com/"+result;
*/

            code_url = code_url + "?";


            return base64;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    // 传入账号密码 验证码 登录  如果登录成功 则直接访问getStudentMain可取的登录信息  否则登录失败
    public  String login(User user) {

        try {
            txtUserName = user.getStuId();
            TextBox2 = user.getPassword();
            txtSecretCode = user.getCode();

            //如果上一次有人登录过了  把后缀名替换掉
            if (STUMAIN.indexOf("=") != STUMAIN.length()) {
                STUMAIN = STUMAIN.substring(0, STUMAIN.indexOf("=") + 1);
            }
            STUMAIN = STUMAIN + txtUserName;


            // 登录必要信息
            FormBody formBody = new FormBody.Builder().
                    add("__VIEWSTATE", __VIEWSTATE).
                    add("txtUserName", txtUserName).
                    add("TextBox2", TextBox2).
                    add("txtSecretCode", txtSecretCode).
                    add("RadioButtonList1", URLEncoder.encode(RadioButtonList1, "GB2312")).
                    add("Button1", Button1).
                    add("lbLanguage", lbLanguage).
                    add("hidPdrs", hidPdrs).
                    add("hidsc", hidsc).build();


            // 登录头信息
            Headers headers = new Headers.Builder().add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                    .add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                    .add("Accept-Language", "zh-CN,zh;q=0.9")
                    .add("Connection", "keep-alive")
                    .add("Host", "jwxt.sontan.net")
                    .add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")

                    .add("Content-Type", "application/x-www-form-urlencoded")
                    .add("Cookie", COOKIES)
                    .add("Content-Length", formBody.contentLength() + "")
                    .add("Referer", "http://jwxt.sontan.net/Default2.aspx").build();


            HttpUtil.Post(main_url, headers, formBody);

            //如果登录成功 则设置登录状态为true 把页面内容给MainContent 并且解析出需要的链接
            String stuInfo = getStudentMain();
            if (stuInfo.contains("同学")) {
                System.out.println("登录成功！");
                System.out.println("1111");
                loginStatus = true;
                MainContent = stuInfo;

                parseStudentMain();
                return stuInfo;
            } else {
                System.out.println("登录失败！");
                return null;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    //获取主页信息
    public  String getStudentMain() {

        return HttpUtil.Get(STUMAIN, Cookieheaders);

    }

    //解析学生主页信息
    public  void parseStudentMain() {
        if (MainContent != "") {
            userModule = new UserModule();

            Document document = Jsoup.parse(MainContent);
            stuName = document.getElementById("xhxm").text().replace("同学", "");
            // 学生辅修报名表
            String stuMinor = document.select("a[href*=xsfxbm.aspx]").attr("href");

            //学生个人课表
            String stuCourse = document.select("a[href*=xskbcx.aspx]").attr("href");

            //学生考试查询
            String stuExam = document.select("a[href*=xskscx.aspx]").attr("href");

            //学生成绩查询
            String stuScore = document.select("a[href*=xscjcx.aspx]").attr("href");

            //学生个人信息
            String stuInfo = document.select("a[href*=xsgrxx.aspx]").attr("href");

            userModule.setStuName(stuName);
            userModule.setStuMinor(stuMinor);
            userModule.setStuCourse(stuCourse);
            userModule.setStuExam(stuExam);
            userModule.setStuScore(stuScore);
            userModule.setStuInfo(stuInfo);

        }


    }


    //获取课程 参数为学期年份 学期期数
    public  String getStuCourse(String xnd,String xqd) {
        if(userModule!=null) {
            return (String) getStuModuleOFPost(userModule.getStuCourse(),xnd,xqd);
        }
        return "";
    }



    //获取课程 参数为学期年份 学期期数
    public  String getStuExam(String xnd,String xqd) {
        if(userModule!=null) {
            return (String) getStuModuleOFPost(userModule.getStuExam(),xnd,xqd);
        }
        return "";
    }



    /*
    获取学生个人信息（全部）
     */
    public  String getStuInfo(){
       if(userModule!=null){
           return getStuModulUtilOFGet(userModule.getStuInfo());
       }
       return "";
    }


    /*
    获取学生辅修信息 包含大一大二 课程名称，成绩 学年平均成绩 以及绩点
     */
    public  String getStuMinor(){
        if(userModule!=null){
            return getStuModulUtilOFGet(userModule.getStuMinor());
        }
        return "";
    }





    //获取成绩 参数分别为 学年 学期 课程性质 按钮名字
    public  String getStuScore(String ddlXN,String ddlXQ,String ddl_kcxz,String btn_xn) {
        if(userModule!=null) {
            try {
                String content = "";

                String url = ModuelUrl + Encode.encode(userModule.getStuScore());

                Headers.Builder builder = new Headers.Builder()
                        .addAll(Cookieheaders);

                //设定未访问过
                if(!scoreStatus){
                    content = HttpUtil.Get(url,builder.add("Referer",STUMAIN).build());
                    getVIEWSTATE(content);
                }

                builder.set("Referer",Encode.encode(url));
                Headers headers = builder.build();


                FormBody form = new FormBody.Builder()
                        .add("__EVENTTARGET","")
                        .add("__EVENTARGUMENT","")
                        .add("__VIEWSTATE",Encode.encode(__VIEWSTATE))
                        .add("hidLanguage","")
                        .add("ddlXN",ddlXN)
                        .add("ddlXQ",ddlXQ)
                        .add("ddl_kcxz",ddl_kcxz)
                        .add("btn_xn",Encode.encode(btn_xn))
                        .build();

                content = HttpUtil.Post(url,headers,form);

                getVIEWSTATE(content);
                scoreStatus =false;

                return  content;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  "";
    }



    //获取个人信息 辅修报名 通用  Get
    public  String getStuModulUtilOFGet(String stuModuleUrl){
        String url = ModuelUrl + Encode.encode(stuModuleUrl);
        Headers headers = new Headers.Builder().addAll(Cookieheaders)
                .set("Referer",Encode.encode(url)).build();

        String content  =  HttpUtil.Get(url,headers);
        return content;
    }



    //获取成绩 考试时间通用  Post
    public  String getStuModuleOFPost(String stuModelUrl, String xnd,String xqd) {
        try {
            String content = "";

            String url = ModuelUrl + Encode.encode(stuModelUrl);

            Headers.Builder builder = new Headers.Builder()
                    .addAll(Cookieheaders);

            content = HttpUtil.Get(url,builder.add("Referer",STUMAIN).build());
            getVIEWSTATE(content);

            /*
            没有学年度和学期的的信息，则发送get请求之后直接返回
            如果有则加入头协议与内容 重新post发送
             */
            if(xnd==null||xqd==null){
                return content;
            }else{

                builder.set("Referer",Encode.encode(url));
                Headers headers = builder.build();


                FormBody form = new FormBody.Builder()
                        .add("__EVENTTARGET","xqd")
                        .add("__EVENTARGUMENT","")
                        .add("__VIEWSTATE",Encode.encode(__VIEWSTATE))
                        .add("xnd",xnd)
                        .add("xqd",xqd)
                        .build();


                content = HttpUtil.Post(url,headers,form);
            }
            getVIEWSTATE(content);

            return  content;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  "";
    }



    public boolean getLoginStatus() {
        return loginStatus;
    }


    public String getStuName() {
        return stuName;
    }


    public String getUserName() {
        return txtUserName;
    }


}
