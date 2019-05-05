package Service;

import pojo.*;

import java.util.HashMap;
import java.util.List;

public interface JwxtService {

    //Finish
    void init();

    //Finish
    String onclickPic();

    //Finish
    String login(User user);

    String getStudentMain();


    //Finish
    List<Course> getStuCourse(String xnd,String xqd);

    //Finish
    List<Exam> getStuExam(String xnd, String xqd);

    //Finish
    StuInfo getStuInfo();

    HashMap<String, StuMinor> getStuMinor();

    //Finish
    List<Score> getStuScore(String ddlXN, String ddlXQ, String ddl_kcxz, String btn_xn);

    //Finish
    boolean getLoginStatus();


    //Finish
    List<Course> parseCourse(String courseStr,String className,String grade, String trem);


}
