package Service;

import Mapper.Course_ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Course_Score;

import java.util.List;

@Service
public class Course_ScoreServiceImpl implements Course_ScoreService{

    @Autowired
    private Course_ScoreMapper course_scoreMapper;

    @Override
    public int insertCourse_Score(Course_Score course_score) {
        return course_scoreMapper.insertCourse_Score(course_score);
    }

    @Override
    public List<Course_Score> queryCourse_Score(String stuId) {
        return course_scoreMapper.queryCourse_Score(stuId);
    }
}
