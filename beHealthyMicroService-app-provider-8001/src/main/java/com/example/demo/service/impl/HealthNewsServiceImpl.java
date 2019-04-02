package com.example.demo.service.impl;

import com.example.demo.dao.HealthNewsDao;
import com.example.demo.entity.HealthNews;
import com.example.demo.service.HealthNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthNewsServiceImpl implements HealthNewsService {
    @Autowired
    private HealthNewsDao healthNewsDao;
    @Override
    public List<HealthNews> getAll() {
        return healthNewsDao.getAll();
    }

    @Override
    public HealthNews findById(Integer nid) {
        return healthNewsDao.findById(nid);
    }

    @Override
    public Integer addHealthNews(HealthNews healthNews) {
        return healthNewsDao.addHealthNews(healthNews);
    }

    @Override
    public Integer updateHealthNews(HealthNews healthNews) {
        return healthNewsDao.updateHealthNews(healthNews);
    }

    @Override
    public Integer deleteHealthNews(Integer nid) {
        return healthNewsDao.deleteHealthNews(nid);
    }
}
