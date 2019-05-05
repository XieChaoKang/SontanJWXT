package pojo;

public class Course_Score {
    private int id;
    private String stuId;
    private String courseName;
    private String score;

    public Course_Score() {
    }

    public Course_Score(String stuId, String course, String score) {
        this.stuId = stuId;
        this.courseName = course;
        this.score = score;
    }


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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return "Course_Score{" +
                "stuId='" + stuId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

}

