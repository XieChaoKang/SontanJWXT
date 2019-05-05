package Service;

import Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;


public interface UserService {

    //插入用户
    int insertUser(User user);

    //通过账号密码查询名字
    String queryName(User user);

    //更改密码
    int updateUser(User user);

}
