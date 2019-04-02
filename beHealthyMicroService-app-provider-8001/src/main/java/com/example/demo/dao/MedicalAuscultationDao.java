package com.example.demo.dao;

import com.example.demo.entity.MedicalAuscultation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MedicalAuscultationDao {
    //查询所有
    public List<MedicalAuscultation> getAll(Integer uid);
    //根据id查询
    public MedicalAuscultation findById(Integer mid);
    //增加
    public Integer addMedicalAus(MedicalAuscultation medicalAuscultation);
    //更新
    public Integer updateMedicalAus(MedicalAuscultation medicalAuscultation);
    //删除
    public Integer deleteMedicalAus(Integer mid);
}
