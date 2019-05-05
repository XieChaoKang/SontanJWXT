package Mapper;

import pojo.User;

public interface UserMapper {

    //插入用户
    int insertUser(User user);

    //通过账号密码查询名字
    String queryName(User user);

    //更改密码
    int updateUser(User user);
}
