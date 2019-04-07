package com.example.demo.controller;

import com.example.demo.entity.CaseHistory;
import com.example.demo.service.CaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caseHistory")
public class CaseHistoryController {
    @Autowired
    private CaseHistoryService caseHistoryService;

    @PostMapping("/getAll/{uid}/{fid}")
    public List<CaseHistory> getAll(@PathVariable("uid")Integer uid,@PathVariable("fid")Integer fid){
        return caseHistoryService.getAll(uid,fid);
    }

    @PostMapping("/findById/{cid}")
    public CaseHistory findById(@PathVariable("cid")Integer cid){
        return caseHistoryService.findById(cid);
    }

    @PostMapping("/addCaseHistory")
    public Integer addCaseHistory(@RequestBody CaseHistory caseHistory){
        return caseHistoryService.addCaseHis(caseHistory);
    }

    @PostMapping("/updateCaseHistory")
    public Integer updateCaseHistory(@RequestBody CaseHistory caseHistory){
        return caseHistoryService.updateCaseHis(caseHistory);
    }

    @PostMapping("/deleteCaseHistory/{cid}")
    public Integer deleteCaseHistory(@PathVariable("cid")Integer cid){
        return caseHistoryService.deleteCaseHis(cid);
    }

}
