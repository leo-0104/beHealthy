package com.example.demo.service.impl;

import com.example.demo.dao.HealthRecordDao;
import com.example.demo.entity.HealthRecord;
import com.example.demo.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {
    @Autowired
    private HealthRecordDao healthRecordDao;
    @Override
    public List<HealthRecord> getAll(Integer uid) {
        return healthRecordDao.getAll(uid);
    }

    @Override
    public HealthRecord findById(Integer hid) {
        return healthRecordDao.findById(hid);
    }

    @Override
    public Integer addHealthRecord(HealthRecord healthRecord) {
        return healthRecordDao.addHealthRecord(healthRecord);
    }

    @Override
    public Integer updateHealthRecord(HealthRecord healthRecord) {
        return healthRecordDao.updateHealthRecord(healthRecord);
    }

    @Override
    public Integer deleteHealthRecord(Integer hid) {
        return healthRecordDao.deleteHealthRecord(hid);
    }
}
