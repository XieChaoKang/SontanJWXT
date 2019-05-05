package Service;

import Mapper.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Exam;
import pojo.Grade_Trem;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamMapper examMapper;

    @Override
    public int insertExam(Exam exam) {
        return examMapper.insertExam(exam);
    }

    @Override
    public List<Exam> queryExamByStuId(Grade_Trem grade_trem) {
        return examMapper.queryExamByStuId(grade_trem);
    }
}
