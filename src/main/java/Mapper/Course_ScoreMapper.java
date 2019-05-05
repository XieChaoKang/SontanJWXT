package Mapper;

import pojo.Course_Score;

import java.util.List;

public interface Course_ScoreMapper {

    //插入一条简单的课程成绩
    int insertCourse_Score(Course_Score course_score);

    //通过学号查课程 成绩
    List<Course_Score> queryCourse_Score(String stuId);
}
