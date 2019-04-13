package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/***
 * 病历信息表
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "be-healthy",type = "caseHistory",shards = 5,replicas = 1)
public class CaseHistory {
    //病历号
    @Id
    @Field(type = FieldType.Integer)
    private int cid;
    //病历原件
    @Field(type = FieldType.Keyword)
    private String photo;
    //就诊人
    @Field(type = FieldType.Text)
    private String visitName;
    //医院名称
    @Field(type = FieldType.Text)
    private String hospitalName;
    //科室
    @Field(type = FieldType.Text)
    private String office;
    //就诊医生
    @Field(type = FieldType.Text)
    private String doctorName;
    //就诊日期
    @Field(type = FieldType.Keyword)
    private String visitDate;
    //体格检查
    @Field(type = FieldType.Text)
    private String checkup;
    //诊断结果
    @Field(type = FieldType.Text)
    private String diagnosisResult;
    //主诉
    @Field(type = FieldType.Text)
    private String mainSuit;
    //现病史
    @Field(type = FieldType.Text)
    private String historyNow;
    //既往史
    @Field(type = FieldType.Text)
    private String historyPast;
    //过敏史
    @Field(type = FieldType.Text)
    private String historyAllergy;
    //家族史
    @Field(type = FieldType.Text)
    private String historyFamily;
    //婚育史
    @Field(type = FieldType.Text)
    private String historyMarriage;
    //处理意见
    @Field(type = FieldType.Text)
    private String suggestion;
    //操作时间
    @Field(type = FieldType.Keyword)
    private String operTime;
    //用户uid
    @Field(type = FieldType.Integer)
    private int uid;
    //家庭成员id
    @Field(type = FieldType.Integer)
    private int fid;
}
