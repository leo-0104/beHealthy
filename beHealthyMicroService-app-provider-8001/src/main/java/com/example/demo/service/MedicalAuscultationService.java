package com.example.demo.service;

import com.example.demo.entity.MedicalAuscultation;

import java.util.List;

public interface MedicalAuscultationService {
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
