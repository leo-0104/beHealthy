package com.example.demo.service;

import com.example.demo.entity.FamilyMember;

import java.util.List;

public interface FamilyMemberService {
    //注册
    public Integer registerMember(FamilyMember member);
    //根据id查询
    public FamilyMember findById(Integer fid);
    //查询所有
    public List<FamilyMember> getAll(Integer uid);
    //更新
    public Integer updateMember(FamilyMember familyMember);
    //删除
    public Integer deleteMember(Integer fid,Integer uid);
}
