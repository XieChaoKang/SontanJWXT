package Controller;

import Service.JwxtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pojo.StuMinor;
import pojo.User;

import java.util.Map;


@Controller
public class LoginController {

    @Autowired
    private JwxtService jwxtService;

    /*
    登录前初始化页面 获取必要的信息
     */
    @ResponseBody
    @RequestMapping("init")
    public String init(){
        System.out.println("init 修改了一次");
        jwxtService.init();
        return jwxtService.onclickPic();
    }

    /*
    登录页面切换验证码
     */
    @ResponseBody
    @RequestMapping("clickCode")
    public String clickCode(){
        return jwxtService.onclickPic();
    }

    /*
    登录
    参数 账号 密码 验证码
     */
    @RequestMapping(value="login", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(User user) {

        return jwxtService.login(user);
    }



    /*
    获取教务系统登录主页
     */
    @ResponseBody
    @RequestMapping(value="getStudentMain", produces = "application/json; charset=utf-8")
    public String getStudentMain(){
        if(jwxtService.getLoginStatus()){
            return jwxtService.getStudentMain();
        }
        return "还未登录,请重新登录！";
    }

    /*
    查询课表
    参数 学年 学期  可不填默认导出本学期课表
     */
    @ResponseBody
    @RequestMapping(value="getStuCourse", produces = "application/json; charset=utf-8")
    public String getStuCourse(){
        return String.valueOf(jwxtService.getStuCourse(null,null));
       // return jwxtService.getStuCourse("2018-2019","2");
    }


    @ResponseBody
    @RequestMapping(value="getStuExam", produces = "application/json; charset=utf-8")
    public String getStuExam(){
        return String.valueOf(jwxtService.getStuExam("2017-2018","1"));
        //return String.valueOf(jwxtService.getStuExam("2018-2019","2"));
    }




    /*
    查询成绩
    参数分别为学年 学期 课程性质(必须加0 eg：01 02 03 不选择为"") 学年成绩
     */
    @ResponseBody
    @RequestMapping(value="getStuScore", produces = "application/json; charset=utf-8")
    public String getStuScore(){

        //return jwxtService.getStuScore("2017-2018","2","03","学年成绩");
        return String.valueOf(jwxtService.getStuScore("2017-2018","","","学年成绩"));
    }

    @ResponseBody
    @RequestMapping(value = "getStuInfo", produces = "application/json;charset=utf-8")
    public String getStuInfo(){
        return jwxtService.getStuInfo().toString();
    }


    @ResponseBody
    //@RequestMapping(value = "getStuMinor", produces = "application/json;charset=utf-8")
        @RequestMapping("getStuMinor")
    public Map getStuMinor(){
        return jwxtService.getStuMinor();
    }


}
