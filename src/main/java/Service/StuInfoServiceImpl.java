package Service;

import Mapper.StuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.StuInfo;

@Service
public class StuInfoServiceImpl implements StuInfoService{

    @Autowired
    private StuInfoMapper stuInfoMapper;

    @Override
    public int insertStuInfo(StuInfo stuInfo) {
        return stuInfoMapper.insertStuInfo(stuInfo);
    }

    @Override
    public StuInfo queryAll(String stuId) {
        return stuInfoMapper.queryAll(stuId);
    }

    @Override
    public String queryClassName(String stuId) {
        return stuInfoMapper.queryClassName(stuId);
    }

    @Override
    public String queryName(String stuId) {
        return stuInfoMapper.queryName(stuId);
    }
}
