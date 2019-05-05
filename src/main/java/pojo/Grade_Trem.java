package pojo;

/*
作为查询课表，考试时间的辅助类
 */
public class Grade_Trem {
    private String stuId; //学号
    private String className;  //班名
    private String grade;   //学年
    private String trem;    //学期
    private String nature; //课程性质


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
}
