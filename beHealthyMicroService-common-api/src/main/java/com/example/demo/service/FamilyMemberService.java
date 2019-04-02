package com.example.demo.service;

import com.example.demo.entity.FamilyMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(value = "microservicecloud-provider-app")
public interface FamilyMemberService {

    @GetMapping("/member/getAll/{uid}")
    public List<FamilyMember> getAll(@PathVariable("uid")Integer uid);

    @GetMapping("/member/findById/{fid}")
    public FamilyMember findById(@PathVariable("fid")Integer fid);

    @PostMapping("/member/registerMember")
    public Integer registerMember(@RequestBody FamilyMember familyMember);

    @PostMapping("/member/updateMember")
    public Integer updateMember(@RequestBody FamilyMember familyMember);

    @PostMapping("/member/deleteMember")
    public Integer deleteMember(@RequestParam("uid") Integer uid, @RequestParam("fid")Integer fid);
}
