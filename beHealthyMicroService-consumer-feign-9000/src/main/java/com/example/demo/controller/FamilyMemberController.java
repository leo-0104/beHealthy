package com.example.demo.controller;

import com.example.demo.entity.FamilyMember;
import com.example.demo.service.FamilyMemberService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/member")
@RestController
public class FamilyMemberController {
    @Autowired
    private FamilyMemberService familyMemberService;

    @PostMapping("/getAll/{uid}")
    public String getAll(@PathVariable("uid")Integer uid){
        return JsonResult.success(familyMemberService.getAll(uid));
    }

    @PostMapping("/findById/{fid}")
    public String findById(@PathVariable("fid")Integer fid){
        FamilyMember familyMember =  familyMemberService.findById(fid);
        if (familyMember == null){
            return JsonResult.failed(-1,"找不到该成员");
        }
        return JsonResult.success(familyMember);
    }

    @PostMapping("/registerMember")
    public String registerMember(@RequestBody FamilyMember familyMember){
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        familyMember.setOperTime(simpleDateFormat.format(date));
        //补全头像路径
        if (familyMember.getProfile() != null){
            familyMember.setProfile("E:\\image\\memberPhoto\\" + familyMember.getProfile());
        }
        Integer num =  familyMemberService.registerMember(familyMember);
        if (num <= 0){
            return JsonResult.failed(-1,"添加成员失败");
        }
        return JsonResult.success(num);
    }

    @PostMapping("/updateMember")
    public String updateMember(@RequestBody FamilyMember familyMember){
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        familyMember.setOperTime(simpleDateFormat.format(date));
        //补全头像路径
        if (familyMember.getProfile() != null){
            familyMember.setProfile("E:\\image\\memberPhoto\\" + familyMember.getProfile());
        }
        Integer num =  familyMemberService.updateMember(familyMember);
        if (num <= 0){
            return JsonResult.failed(-1,"更新家庭成员信息失败");
        }
        return JsonResult.success(num);
    }

    @PostMapping("/deleteMember/{uid}/{fid}")
    public String deleteMember(@PathVariable("uid") Integer uid, @PathVariable("fid")Integer fid){
        Integer num =  familyMemberService.deleteMember(uid,fid);
        if (num <= 0){
            return JsonResult.failed(-1,"删除家庭成员信息失败");
        }
        return JsonResult.success(num);
    }
}

