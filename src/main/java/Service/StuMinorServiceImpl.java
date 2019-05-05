package Service;

import Mapper.StuMinorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.StuMinor;

@Service
public class StuMinorServiceImpl implements StuMinorService{

    @Autowired
    private StuMinorMapper stuMinorMapper;

    @Override
    public int insertStuMinor(StuMinor stuMinor) {
        return stuMinorMapper.insertStuMinor(stuMinor);
    }

    @Override
    public StuMinor queryStuMinorByStuId(String stuId) {
        return stuMinorMapper.queryStuMinorByStuId(stuId);
    }
}
