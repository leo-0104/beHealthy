package com.example.demo.dao;

import com.example.demo.entity.HealthNews;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthNewsDao {
    //查询所有
    public List<HealthNews> getAll();
    //查询所有
    public List<HealthNews> getAllByCondition();
    //根据id查询
    public HealthNews findById(Integer nid);
    //增加
    public Integer addHealthNews(HealthNews healthNews);
    //更新
    public Integer updateHealthNews(HealthNews healthNews);
    //删除
    public Integer deleteHealthNews(Integer nid);
}
