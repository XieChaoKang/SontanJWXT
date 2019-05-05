package pojo;

public class Score {

    private  int id;

    private String stuId;  //学号

    private String grade;  //学年

    private int trem;  //学期

    private String nature; //课程性质

    private String courseName;  //课程名称

    private float credit; //学分

    private float point; //绩点

    private float score; //成绩


    @Override
    public String toString() {
        return "Score{" +
                "stuId='" + stuId + '\'' +
                ", grade='" + grade + '\'' +
                ", trem='" + trem + '\'' +
                ", nature='" + nature + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", point=" + point +
                ", score=" + score +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
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

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

}
