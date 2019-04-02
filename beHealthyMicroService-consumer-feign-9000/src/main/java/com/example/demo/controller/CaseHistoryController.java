package com.example.demo.controller;

import com.example.demo.entity.CaseHistory;
import com.example.demo.service.CaseHistoryService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caseHistory")
public class CaseHistoryController {
    @Autowired
    private CaseHistoryService caseHistoryService;

    @GetMapping("/getAll/{id}/{flag}")
    public String getAll(@PathVariable("id")Integer id, @PathVariable("flag")Boolean flag){
        return JsonResult.success(caseHistoryService.getAll(id,flag));
    }

    @GetMapping("/findById/{cid}")
    public String findById(@PathVariable("cid")Integer cid){
        CaseHistory caseHistory =  caseHistoryService.findById(cid);
        if (caseHistory == null)
            return JsonResult.failed(-1,"查询不到该病历");
        return JsonResult.success(caseHistory);
    }

    @PostMapping("/addCaseHistory")
    public String addCaseHistory(@RequestBody CaseHistory caseHistory){
        Integer num =  caseHistoryService.addCaseHistory(caseHistory);
        if (num <= 0)
            return JsonResult.failed(-1,"保存病历信息失败");
        return JsonResult.success();
    }

    @PostMapping("/updateCaseHistory")
    public String updateCaseHistory(@RequestBody CaseHistory caseHistory){
        Integer num =  caseHistoryService.updateCaseHistory(caseHistory);
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
