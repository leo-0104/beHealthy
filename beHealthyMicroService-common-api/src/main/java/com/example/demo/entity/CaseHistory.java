package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 病历信息表
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CaseHistory {
    //病历号
    private int cid;
    //病历原件
    private String photo;
    //就诊人
    private String visitName;
    //医院名称
    private String hospitalName;
    //科室
    private String office;
    //就诊医生
    private String doctorName;
    //就诊日期
    private String visitDate;
    //体格检查
    private String checkup;
    //诊断结果
    private String diagnosisResult;
    //主诉
    private String mainSuit;
    //现病史
    private String historyNow;
    //既往史
    private String historyPast;
    //过敏史
    private String historyAllergy;
    //家族史
    private String historyFamily;
    //婚育史
    private String historyMarriage;
    //处理意见
    private String suggestion;
    //操作时间
    private String operTime;
    //用户uid
    private int uid;
    //家庭成员id
    private int fid;
}
