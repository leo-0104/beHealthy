package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 健康资讯信息表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthNews implements Serializable {
    //编号
    private int nid;
    //标题
    private String title;
    //作者
    private String author;
    //封面截图
    private String coverUrl;
    //状态
    private int status;
    //是否置顶
    private int isTop;
    //内容
    private String content;
    //操作时间
    private String operTime;
}
