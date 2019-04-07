package com.example.demo.controller;

import com.example.demo.entity.CaseHistory;
import com.example.demo.service.CaseHistoryService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/caseHistory")
public class CaseHistoryController {
    @Autowired
    private CaseHistoryService caseHistoryService;

    @PostMapping("/getAll/{uid}/{fid}")
    public String getAll(@PathVariable("uid")Integer uid, @PathVariable("fid")Integer fid){
        return JsonResult.success(caseHistoryService.getAll(uid,fid));
    }

    @PostMapping("/findById/{cid}")
    public String findById(@PathVariable("cid")Integer cid){
        CaseHistory caseHistory =  caseHistoryService.findById(cid);
        if (caseHistory == null)
            return JsonResult.failed(-1,"查询不到该病历");
        return JsonResult.success(caseHistory);
    }

    @PostMapping("/addCaseHistory")
    public String addCaseHistory(@RequestBody CaseHistory caseHistory){
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        caseHistory.setOperTime(simpleDateFormat.format(date));
        //补全头像路径
        if (caseHistory.getPhoto() != null){
            caseHistory.setPhoto("E:\\image\\medicalRecordPhoto\\" + caseHistory.getPhoto());
        }
        Integer num =  caseHistoryService.addCaseHistory(caseHistory);
        if (num <= 0)
            return JsonResult.failed(-1,"保存病历信息失败");
        return JsonResult.success();
    }

    @PostMapping("/updateCaseHistory")
    public String updateCaseHistory(@RequestBody CaseHistory caseHistory){
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        caseHistory.setOperTime(simpleDateFormat.format(date));
        Integer num = caseHistoryService.updateCaseHistory(caseHistory);
        if (num <= 0)
            return JsonResult.failed(-1,"更新病历信息失败");
        return JsonResult.success();
    }

    @PostMapping("/deleteCaseHistory/{cid}")
    public String deleteCaseHistory(@PathVariable("cid")Integer cid){
        Integer num = caseHistoryService.deleteCaseHistory(cid);
        if (num <= 0)
            return JsonResult.failed(-1,"删除病历信息失败");
        return JsonResult.success();
    }

}
