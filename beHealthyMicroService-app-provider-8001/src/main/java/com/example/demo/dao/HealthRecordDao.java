package com.example.demo.dao;

import com.example.demo.entity.HealthRecord;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface HealthRecordDao {
    //查询所有
    public List<HealthRecord> getAll(Integer uid);
    //根据id查询
    public HealthRecord findById(Integer hid);
    //增加
    public Integer addHealthRecord(HealthRecord healthRecord);
    //更新
    public Integer updateHealthRecord(HealthRecord healthRecord);
    //删除
    public Integer deleteHealthRecord(Integer hid);
}
