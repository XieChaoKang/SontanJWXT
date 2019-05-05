package Service;

import Mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Course;
import pojo.Grade_Trem;

import java.util.List;

@Service
public class CourseServiceImpl implements  CourseService{

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public int insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    @Override
    public List<Course> queryCourseByClassName(Grade_Trem grade_trem) {
        return courseMapper.queryCourseByClassName(grade_trem);
    }
}
