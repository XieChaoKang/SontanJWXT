package pojo;

public class Course {
    private int id;  //编号
    private String courseName;  //课程名称
    private String teachName;  //教师名称
    private String day;  //上课周几
    private int stratTime;  //第几节开始
    private int endTime;   //第几节节书
    private int startWeek;  //第几周开始
    private int endWeek;   //第几周结束
    private String address; //上课地点
    private String className; //这个课程属于哪个班级的  可通过班级查询该班的课程
    private String grade; //年级
    private String trem;  //学期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStratTime() {
        return stratTime;
    }

    public void setStratTime(int stratTime) {
        this.stratTime = stratTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTrem() {
        return trem;
    }

    public void setTrem(String trem) {
        this.trem = trem;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", teachName='" + teachName + '\'' +
                ", day='" + day + '\'' +
                ", stratTime=" + stratTime +
                ", endTime=" + endTime +
                ", startWeek=" + startWeek +
                ", endWeek=" + endWeek +
                ", address='" + address + '\'' +
                ", className='" + className + '\'' +
                ", grade='" + grade + '\'' +
                ", trem='" + trem + '\'' +
                '}';
    }
}
