package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    //登陆
    public User login(User user);

    //注册(返回注册的id)
    public Integer register(User user);

    //更新
    public Integer updateUser(User user);

    //查询
    public User findById(int id);
}
