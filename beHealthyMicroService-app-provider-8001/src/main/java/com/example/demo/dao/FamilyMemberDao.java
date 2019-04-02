package com.example.demo.dao;

import com.example.demo.entity.FamilyMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FamilyMemberDao {
    //注册
    public Integer registerMember(FamilyMember member);
    //根据id查询
    public FamilyMember findById(Integer fid);
    //查询所有
    public List<FamilyMember> getAll(Integer uid);
    //更新
    public Integer updateMember(FamilyMember familyMember);
    //删除
    public Integer deleteMember(@Param("fid") Integer fid,@Param("uid") Integer uid);
}
