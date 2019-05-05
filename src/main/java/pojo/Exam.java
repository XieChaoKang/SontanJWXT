package pojo;

public class Exam {

    private int id;

    private String stuId;  //学号

    private String grade;  //学年

    private int trem;  //学期

    private String courseName;  //课程名称

    private String examTime; //考试时间

    private String address; //考试地点

    private String seat;  //座位号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getTrem() {
        return trem;
    }

    public void setTrem(int trem) {
        this.trem = trem;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "stuId='" + stuId + '\'' +
                ", grade='" + grade + '\'' +
                ", trem=" + trem +
                ", courseName='" + courseName + '\'' +
                ", examTime='" + examTime + '\'' +
                ", address='" + address + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }
}
