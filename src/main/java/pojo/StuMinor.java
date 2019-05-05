package pojo;

import java.util.List;

public class StuMinor {
    private int id;
    private String  stuId;
    private float averageScoreOne;
    private float averageScoreTwo;
    private float averagePoint;
    private float totalCredit;
    private List<Course_Score> stuScoreInfos;

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

    public float getAverageScoreOne() {
        return averageScoreOne;
    }

    public void setAverageScoreOne(float averageScoreOne) {
        this.averageScoreOne = averageScoreOne;
    }

    public float getAverageScoreTwo() {
        return averageScoreTwo;
    }

    public void setAverageScoreTwo(float averageScoreTwo) {
        this.averageScoreTwo = averageScoreTwo;
    }

    public float getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(float averagePoint) {
        this.averagePoint = averagePoint;
    }

    public float getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(float totalCredit) {
        this.totalCredit = totalCredit;
    }

    public List<Course_Score> getStuScoreInfos() {
        return stuScoreInfos;
    }

    public void setStuScoreInfos(List<Course_Score> stuScoreInfos) {
        this.stuScoreInfos = stuScoreInfos;
    }

    @Override
    public String toString() {
        return "StuMinor{" +
                "stuId=" + stuId +
                ", averageScoreOne=" + averageScoreOne +
                ", averageScoreTwo=" + averageScoreTwo +
                ", averagePoint=" + averagePoint +
                ", totalCredit=" + totalCredit +
                ", stuScoreInfos=" + stuScoreInfos +
                '}';
    }
}
