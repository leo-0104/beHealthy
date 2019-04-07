package com.example.demo.dao;

import com.example.demo.entity.CaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaseHistoryDao {
    //查询所有
    public List<CaseHistory> getAll(@Param("uid") Integer uid,@Param("fid") Integer fid);
    //根据病历id查询
    public CaseHistory findById(Integer cid);
    //增加病历
    public Integer addCaseHis(CaseHistory caseHistory);
    //修改
    public Integer updateCaseHis(CaseHistory caseHistory);
    //删除
    public Integer deleteCaseHis(Integer cid);
}
