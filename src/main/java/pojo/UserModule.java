package pojo;

public class UserModule {
    private int id;
    private  String stuName; //学生姓名
    private  String stuInfo;  //学生个人信息
    private  String stuScore;  //学生成绩查询
    private  String stuExam;  //学生考试查询
    private  String stuCourse; //学生个人课表
    private  String stuMinor; // 学生辅修报名表

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(String stuInfo) {
        this.stuInfo = stuInfo;
    }

    public String getStuScore() {
        return stuScore;
    }

    public void setStuScore(String stuScore) {
        this.stuScore = stuScore;
    }

    public String getStuExam() {
        return stuExam;
    }

    public void setStuExam(String stuExam) {
        this.stuExam = stuExam;
    }

    public String getStuCourse() {
        return stuCourse;
    }

    public void setStuCourse(String stuCourse) {
        this.stuCourse = stuCourse;
    }

    public String getStuMinor() {
        return stuMinor;
    }

    public void setStuMinor(String stuMinor) {
        this.stuMinor = stuMinor;
    }
}
