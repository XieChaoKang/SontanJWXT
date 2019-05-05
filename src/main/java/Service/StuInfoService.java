package Service;

import pojo.StuInfo;

public interface StuInfoService {

    //插入一条信息
    int insertStuInfo(StuInfo stuInfo);

    //学号查询信息
    StuInfo queryAll(String stuId);

    //学号查询班级
    String queryClassName(String stuId);

    //学号查询名字
    String queryName(String stuId);
}
