package com.example.demo.service;

import com.example.demo.entity.CaseHistory;

import java.util.List;

public interface CaseHistoryService {
    //查询所有
    public List<CaseHistory> getAll(Integer id, Boolean flag);
    //根据病历id查询
    public CaseHistory findById(Integer cid);
    //增加病历
    public Integer addCaseHis(CaseHistory caseHistory);
    //修改
    public Integer updateCaseHis(CaseHistory caseHistory);
    //删除
    public Integer deleteCaseHis(Integer cid);
}
