package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 家庭成员信息表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FamilyMember {
    //编号
    private int fid;
    //姓名
    private String name;
    //头像路径
    private String profile;
    //性别
    private int gender;
    //出生日期
    private String birthday;
    //关系
    private String relation;
    //用户uid
    private int uid;
    //操作时间
    private String operTime;
}
