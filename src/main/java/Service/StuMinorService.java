package Service;

import pojo.StuMinor;

public interface StuMinorService {


    // 插入一条辅修成绩
    int insertStuMinor(StuMinor stuMinor);


    //查询辅修成绩信息
    StuMinor queryStuMinorByStuId(String stuId);
}
