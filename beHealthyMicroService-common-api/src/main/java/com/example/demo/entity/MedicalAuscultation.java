package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 就医听诊表
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalAuscultation {
    //编号
    private int mid;
    //音频地址
    private String videoUrl;
    //音频内容
    private String content;
    //用户id
    private int uid;
    //操作时间
    private String operTime;
}
