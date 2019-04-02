package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    //用户id
    private int uid;
    //账号名
    private String accountName;
    //密码
    private String password;
    //性别
    private int gender;
    //出生日期
    private String birthday;
    //头像路径
    private String profile;
    //手机号码
    private String phone;
    //邮箱地址
    private String email;
    //注册时间
    private String registerTime;


}
