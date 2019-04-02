package com.example.demo.controller;

import com.example.demo.entity.HealthRecord;
import com.example.demo.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthRecord")
public class HealthRecordController {
    @Autowired
    private HealthRecordService healthRecordService;

    @GetMapping("/getAll/{uid}")
    public List<HealthRecord> getAll(@PathVariable("uid") Integer uid) {
        return healthRecordService.getAll(uid);
    }

    @GetMapping("/findById/{hid}")
    public HealthRecord findById(@PathVariable("hid") Integer hid) {
        return healthRecordService.findById(hid);
    }

    @PostMapping("/addHealthRecord")
    public Integer addHealthRecord(@RequestBody HealthRecord healthRecord) {
        return healthRecordService.addHealthRecord(healthRecord);
    }
    @PostMapping("/updateHealthRecord")
    public Integer updateHealthRecord(@RequestBody HealthRecord healthRecord) {
        return healthRecordService.updateHealthRecord(healthRecord);
    }
    @PostMapping("/deleteHealthRecord/{hid}")
    public Integer deleteHealthRecord(@PathVariable("hid") Integer hid) {
        return healthRecordService.deleteHealthRecord(hid);
    }
}
