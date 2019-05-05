package Service;

import pojo.Exam;
import pojo.Grade_Trem;

import java.util.List;

public interface ExamService {

    //插入一条考试信息
    int insertExam(Exam exam);


    //通过学号 学期 学期查询考试信息
    List<Exam> queryExamByStuId(Grade_Trem grade_trem);
}
