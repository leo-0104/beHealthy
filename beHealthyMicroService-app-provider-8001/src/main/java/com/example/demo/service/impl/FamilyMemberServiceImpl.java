package com.example.demo.service.impl;

import com.example.demo.dao.FamilyMemberDao;
import com.example.demo.entity.FamilyMember;
import com.example.demo.service.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {
    @Autowired
    private FamilyMemberDao familyMemberDao;
    @Override
    public Integer registerMember(FamilyMember member) {
        return familyMemberDao.registerMember(member);
    }

    @Override
    public FamilyMember findById(Integer fid) {
        return familyMemberDao.findById(fid);
    }

    @Override
    public List<FamilyMember> getAll(Integer uid) {
        return familyMemberDao.getAll(uid);
    }

    @Override
    public Integer updateMember(FamilyMember familyMember) {
        return familyMemberDao.updateMember(familyMember);
    }

    @Override
    public Integer deleteMember(Integer fid, Integer uid) {
        return familyMemberDao.deleteMember(fid,uid);
    }
}
