package com.example.demo.service.impl;

import com.example.demo.dao.MedicalAuscultationDao;
import com.example.demo.entity.MedicalAuscultation;
import com.example.demo.service.MedicalAuscultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalAuscultationServiceImpl implements MedicalAuscultationService {
    @Autowired
    private MedicalAuscultationDao medicalAuscultationDao;
    @Override
    public List<MedicalAuscultation> getAll(Integer uid) {
        return medicalAuscultationDao.getAll(uid);
    }

    @Override
    public MedicalAuscultation findById(Integer mid) {
        return medicalAuscultationDao.findById(mid);
    }

    @Override
    public Integer addMedicalAus(MedicalAuscultation medicalAuscultation) {
        return medicalAuscultationDao.addMedicalAus(medicalAuscultation);
    }

    @Override
    public Integer updateMedicalAus(MedicalAuscultation medicalAuscultation) {
        return medicalAuscultationDao.updateMedicalAus(medicalAuscultation);
    }

    @Override
    public Integer deleteMedicalAus(Integer mid) {
        return medicalAuscultationDao.deleteMedicalAus(mid);
    }
}
