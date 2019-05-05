package Service;

import Util.Jwxt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;

import java.util.*;


@Service
public class JwxtServiceImpl implements JwxtService {

    private Jwxt jwxt = new Jwxt();

    private String stuId;
    private String stuName;

    @Autowired
    private UserService userService;

    @Autowired
    private Course_ScoreService course_scoreService;

    @Autowired
    private StuMinorService stuMinorService;

    @Autowired
    private StuInfoService stuInfoService;

    @Autowired
    private ExamService examService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;


    @Override
    public void init() {
        jwxt.init();
    }

    @Override
    public String onclickPic() {
        return jwxt.onclickPic();
    }

    @Override
    public String login(User user) {
        stuId = user.getStuId();
        System.out.println("user:"+user);
        String content = jwxt.login(user);
        System.out.println(content);
        //登录成功 输入数据库
        if (jwxt.loginStatus){
            System.out.println("登录情况："+jwxt.loginStatus);
            stuName = Jsoup.parse(content).select("#xhxm").text().replaceAll("同学","");
            user.setUserName(stuName);
            //登录成功 账号密码插入数据库
            userService.insertUser(user);
            System.out.println(user);
            return  getStuInfo().toString();
        }
        return "还未登录";

    }

    @Override
    public String getStudentMain() {
        return jwxt.getStudentMain();
    }



    /*
    获取辅修报名表  包含大一大二成绩 绩点 平均分等信息
     */

    @Override
    public HashMap<String, StuMinor> getStuMinor() {
        String content = jwxt.getStuMinor();
        StuMinor stuMinor = new StuMinor();

        Document document = Jsoup.parse(content);

        //获取课程名称 成绩行
        Elements elements = document.select("#Table1>tbody tr:gt(0) td");

        //获取课程行
        Elements courseElement = elements.select("span[id^=kc]");
        //获取成绩行
        Elements scoreElement = elements.select("span[id^=cj]");

        //获取每学年平均绩点 成绩 以及总学分
        String averageScoreOne = elements.select("span[id=pz1]").text();
        String averageScoreTwo = elements.select("span[id=pz2]").text();
        String averagePoint = elements.select("span[id=pjjd]").text();
        String totalCredit = elements.select("span[id=zxf]").text();

        //赋值
        List<Course_Score> course_scoreList = new ArrayList();
        for (int i =0;i<courseElement.size();i++){

            String course=courseElement.eq(i).text();

            if(course.length()>0){

                String score=scoreElement.eq(i).text();

                Course_Score course_score = new Course_Score(stuId,course,score);

                //插入成绩分数
                course_scoreService.insertCourse_Score(course_score);

                course_scoreList.add(course_score);
            }
        }

        //设置辅修表属性的信息
        stuMinor.setStuId(stuId);

        if (averageScoreOne!=""){
            stuMinor.setAverageScoreOne(Float.parseFloat(averageScoreOne));
        }

        if (averageScoreTwo!=""){
            stuMinor.setAverageScoreTwo(Float.parseFloat(averageScoreTwo));
        }

        if (averagePoint!=""){
            stuMinor.setAveragePoint(Float.parseFloat(averagePoint));
        }

        if (totalCredit!=""){
            stuMinor.setTotalCredit(Float.parseFloat(totalCredit));
        }

        stuMinor.setStuScoreInfos(course_scoreList);

        //插入辅修成绩
        stuMinorService.insertStuMinor(stuMinor);

        Map<String, StuMinor> map = new HashMap<>();
        map.put("stuMinor",stuMinor);

        return (HashMap<String, StuMinor>) map;

    }


    @Override
    public boolean getLoginStatus() {
        return jwxt.getLoginStatus();
    }





    /*
    学生个人详细
     */
    @Override
    public StuInfo getStuInfo() {
        String content = jwxt.getStuInfo();

        Document document = Jsoup.parse(content);

        //学号 上方已有
        //姓名 上方已有

        //性别
        String sex = document.select("#lbl_xb").text();

        //生日
        String birthday = document.select("#lbl_csrq").text();

        //家庭地址
        String homeAddress = document.select("#lbl_lydq").text();

        //院系
        String department = document.select("#lbl_xy").text();

        //班级
        String className = document.select("#lbl_xzb").text();

        //专业
        String major = document.select("#lbl_zyfx").text();

        StuInfo studentInfo  = new StuInfo();
        studentInfo.setStuId(stuId);
        studentInfo.setStuName(stuName);
        studentInfo.setSex(sex);
        studentInfo.setBirthday(birthday);
        studentInfo.setHomeAddress(homeAddress);
        studentInfo.setDepartment(department);
        studentInfo.setClassName(className);
        studentInfo.setMajor(major);

        //System.out.println("studentInfo："+studentInfo);
        //插入学生信息
        stuInfoService.insertStuInfo(studentInfo);

        return studentInfo;
    }

    /*
    获取考试时间
     */
    @Override
    public List<Exam> getStuExam(String xnd, String xqd) {

        String content = jwxt.getStuExam(xnd, xqd);

       Document document = Jsoup.parse(content);

        String grade = document.select("select[name=xnd] option[selected=selected]").text();
        String trem = document.select("select[name=xqd] option[selected=selected]").text();

        Elements elements = document.select("#DataGrid1>tbody tr:gt(0)");
        Exam exam[] = new Exam[elements.size()];
        for (int i = 0;i<exam.length;i++){
            exam[i] = new Exam();
            exam[i].setStuId(stuId);
            exam[i].setGrade(grade);
            exam[i].setTrem(Integer.parseInt(trem));
        }

        for (int i =0;i<elements.size();i++){
            Elements element = elements.eq(i);

            //课程名字
            String courseName = element.select("td:eq(1)").text();
            exam[i].setCourseName(courseName);

            //考试时间
            String examTime = element.select("td:eq(3)").text();
            exam[i].setExamTime(examTime);

            //考试地点
            String address = element.select("td:eq(4)").text();
            exam[i].setAddress(address);

            //座位号
            String seat = element.select("td:eq(6)").text();
            exam[i].setSeat(seat);

            //System.out.println(exam[i]);
            //插入考试时间
            examService.insertExam(exam[i]);
        }
        List<Exam> examList = new ArrayList<>();
        examList.addAll(Arrays.asList(exam));


        return examList;
    }

    /*
    获取成绩
     */
    @Override
        public List<Score> getStuScore(String ddlXN, String ddlXQ, String ddl_kcxz, String btn_xn) {
        String content = jwxt.getStuScore(ddlXN,ddlXQ,ddl_kcxz,btn_xn);

        Document document = Jsoup.parse(content);

        //获取学号
        String stuId = document.select("#lbl_xh").text().replace("学号：","");


        Elements elements =  document.select(".datelist>tbody tr:gt(0)");


        Score scores[] = new Score[elements.size()];

        for(int i=0;i<scores.length;i++){
            scores[i]= new Score();
            scores[i].setStuId(stuId);
            scores[i].setGrade(ddlXN);
        }

        List<Score> scoreList = new ArrayList<>();

        for(int i =0;i<elements.size();i++){

            Elements element = elements.eq(i);

            //获取学期
            String trem =  element.select("td:eq(1)").text();
            scores[i].setTrem(Integer.parseInt(trem));

            //获取课程名字
            String courseName = element.select("td:eq(3)").text();
            scores[i].setCourseName(courseName);

            //获取课程性质
            String nature = element.select("td:eq(4)").text();
            scores[i].setNature(nature);

            //获取学分
            String credit = element.select("td:eq(6)").text();
            scores[i].setCredit(Float.parseFloat(credit));

            //获取绩点
            String point = element.select("td:eq(7)").text();
            scores[i].setPoint(Float.parseFloat(point));


            //获取成绩
            String score = element.select("td:eq(8)").text();
            scores[i].setScore(Float.parseFloat(score));

            //插入分数信息
            scoreService.insertScore(scores[i]);

            scoreList.add(scores[i]);

        }


        return scoreList;
    }

    /*
    解析课程
     */
    @Override
    public List<Course> parseCourse(String courseStr,String className,String grade, String trem){

        List<Course> courseList = new ArrayList<>();

        if (courseStr != "") {

            String strs[] = courseStr.split("---");

            Course[] course = new Course[strs.length / 4];
            for (int i = 0; i < course.length; i++) {
                course[i] = new Course();
                course[i].setClassName(className);
                course[i].setGrade(grade);
                course[i].setTrem(trem);
            }

            for (int i = 4; i < strs.length + 4; i++) {
                int num = (i / 4) - 1;
                int j = i - 4;

                //课程名
                if (i % 4 == 0) {
                    String courseName = strs[j];
                    course[num].setCourseName(courseName);
                }

                //课程时间 地点 周数等信息
                if (i % 4 == 1) {
                    String str = strs[j];

                    //防止网课 每周提示周几
                    if (str.indexOf("{") != 0) {
                        //周几
                        String day = str.substring(1, 2);
                        course[num].setDay(day);
                        //开始节
                        String startTime = str.substring(str.indexOf("第") + 1, str.indexOf(","));
                        course[num].setStratTime(Integer.parseInt(startTime));

                        //结束节
                        String endTime = str.substring(str.lastIndexOf(",") + 1, str.indexOf("节"));
                        course[num].setEndTime(Integer.parseInt(endTime));
                    }


                    //设置开始周
                    String startWeek = str.substring(str.lastIndexOf("第") + 1, str.lastIndexOf("-"));
                    course[num].setStartWeek(Integer.parseInt(startWeek));

                    //设置结束周
                    String endWeek = str.substring(str.lastIndexOf("-") + 1, str.lastIndexOf("周")).replaceAll("[\\D]]*", "");
                    course[num].setEndWeek(Integer.parseInt(endWeek));

                }
                //教师名字
                if (i % 4 == 2) course[num].setTeachName(strs[j]);

                //教师地址
                if (i % 4 == 3) course[num].setAddress(strs[j]);
            }

            courseList.addAll(Arrays.asList(course));

        }
        return  courseList;
    }


    /*
    获取课程
     */
    @Override
    public List<Course> getStuCourse(String xnd, String xqd) {


        String content =  jwxt.getStuCourse(xnd,xqd);
        Document document = Jsoup.parse(content);

        //获取班级名称 存入数据库 17计算机科学与技术（x）班
        String className = document.select("#Label9").text().replace("行政班：","");

        // 获取年级2018-2019
        String grade =  document.select("select[name=xnd]>option[selected=selected]").text();

        //获取学期 1
        String trem =  document.select("select[name=xqd]>option[selected=selected]").text();

        System.out.println("数据："+className+":"+grade+":"+trem);


        //获取课程表数据的元素
        Elements elements  = document.select("#Table1 tr:gt(1)").select("td[align=Center][rowspan!='']");

        StringBuffer sb = new StringBuffer();

        //遍历Elements
        for(int j=0;j<elements.size();j++){

            //取每一个元素的值 通过<br>切割数组
            String result = elements.eq(j).html();
            String [] strs = result.split("<br>");

            //遍历该数组
            for(int i=0;i<strs.length;i++){
                //td 剔除 长度为空 内容为0 包含有red的数据
                if (!((strs[i].length()==0) || (strs[i]=="") ||(strs[i].contains("red")))){
                    //td内容 再次剔除含有年份 时间的内容
                    if (strs[i].contains("年") || strs[i].contains("周周")) {i++;continue;}

                    sb.append(strs[i]+"---");
                }
            }
        }

        List<Course> courseList = parseCourse(sb.toString(),className,grade,trem);

        for(int i = 0;i<courseList.size();i++){
            courseService.insertCourse(courseList.get(i));
        }

        return courseList;
    }
}
