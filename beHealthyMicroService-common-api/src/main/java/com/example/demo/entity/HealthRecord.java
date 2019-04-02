package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 康复记录表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthRecord {
    //编号
    private int hid;
    //日期
    private String date;
    //时间
    private String time;
    //食品/饮料摄入
    private String food;
    //运动情况描述
    private String sport;
    //症状描述
    private String symptom;
    //用户id
    private int uid;
    //操作时间
    private String operTime;
}
