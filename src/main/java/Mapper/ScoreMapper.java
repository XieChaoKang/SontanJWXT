package Mapper;

import pojo.Grade_Trem;
import pojo.Score;

import java.util.List;

public interface ScoreMapper {

    //插入一门成绩
    int insertScore(Score score);

    //通过学号 学年 学期 课程性质获取成绩
    List<Score> queryScoreByStuId(Grade_Trem grade_trem);
}
