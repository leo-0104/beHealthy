package com.example.demo.service.impl;

import com.example.demo.dao.CaseHistoryDao;
import com.example.demo.entity.CaseHistory;
import com.example.demo.service.CaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseHistoryServiceImpl implements CaseHistoryService {
    @Autowired
    private CaseHistoryDao caseHistoryDao;
    @Override
    public List<CaseHistory> getAll(Integer uid, Integer fid) {
        return caseHistoryDao.getAll(uid,fid);
    }

    @Override
    public List<CaseHistory> getAll(Integer uid, Integer fid, String query) {
        return caseHistoryDao.getAllByQuery(uid,fid,query);
    }

    @Override
    public CaseHistory findById(Integer cid) {
        return caseHistoryDao.findById(cid);
    }

    @Override
    public Integer addCaseHis(CaseHistory caseHistory) {
        return caseHistoryDao.addCaseHis(caseHistory);
    }

    @Override
    public Integer updateCaseHis(CaseHistory caseHistory) {
        return caseHistoryDao.updateCaseHis(caseHistory);
    }

    @Override
    public Integer deleteCaseHis(Integer cid) {
        return caseHistoryDao.deleteCaseHis(cid);
    }
}
