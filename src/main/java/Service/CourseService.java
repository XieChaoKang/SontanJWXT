package Service;

import pojo.Course;
import pojo.Grade_Trem;

import java.util.List;

public interface CourseService {

    //插入一条课程数据
    int insertCourse(Course course);

    //通过班级 学年 学期查课程
    List<Course> queryCourseByClassName(Grade_Trem course_cgt);
}
