package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    //登陆
    public User login(User user);

    //注册
    public Integer register(User user);

    //更新
    public Integer updateUser(User user);

    //根据id查询
    public User findById(int id);
}
