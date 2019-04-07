package com.example.demo.controller;

import com.example.demo.entity.FamilyMember;
import com.example.demo.service.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/member")
@RestController
public class FamilyMemberController {
    @Autowired
    private FamilyMemberService familyMemberService;

    @PostMapping("/getAll/{uid}")
    public List<FamilyMember> getAll(@PathVariable("uid")Integer uid){
        return familyMemberService.getAll(uid);
    }

    @PostMapping("/findById/{fid}")
    public FamilyMember findById(@PathVariable("fid")Integer fid){
        return familyMemberService.findById(fid);
    }

    @PostMapping("/registerMember")
    public Integer registerMember(@RequestBody FamilyMember familyMember){
        return familyMemberService.registerMember(familyMember);
    }

    @PostMapping("/updateMember")
    public Integer updateMember(@RequestBody FamilyMember familyMember){
        return familyMemberService.updateMember(familyMember);
    }

    @PostMapping("/deleteMember/{uid}/{fid}")
    public Integer deleteMember(@PathVariable("uid") Integer uid, @PathVariable("fid")Integer fid){
        return familyMemberService.deleteMember(fid,uid);
    }
}
