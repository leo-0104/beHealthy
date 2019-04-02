package com.example.demo.controller;

import com.example.demo.entity.HealthRecord;
import com.example.demo.service.HealthRecordService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthRecord")
public class HealthRecordController {
    @Autowired
    private HealthRecordService healthRecordService;

    @GetMapping("/getAll/{uid}")
    public String getAll(@PathVariable("uid") Integer uid) {
        return JsonResult.success(healthRecordService.getAll(uid));
    }

    @GetMapping("/findById/{hid}")
    public String findById(@PathVariable("hid") Integer hid) {
        HealthRecord healthRecord = healthRecordService.findById(hid);
        if (healthRecord == null)
            return JsonResult.failed(-1,"找不到该康复记录表");
        return JsonResult.success(healthRecord);
    }

    @PostMapping("/addHealthRecord")
    public String addHealthRecord(@RequestBody HealthRecord healthRecord) {
        Integer num = healthRecordService.addHealthRecord(healthRecord);
        if (num <= 0)
            return JsonResult.failed(-1,"添加康复记录信息失败");
        return JsonResult.success();
    }
    @PostMapping("/updateHealthRecord")
    public String  updateHealthRecord(@RequestBody HealthRecord healthRecord) {
        Integer num =  healthRecordService.updateHealthRecord(healthRecord);
        if (num <= 0)
            return JsonResult.failed(-1,"更新康复记录信息失败");
        return JsonResult.success();
    }
    @PostMapping("/deleteHealthRecord/{hid}")
    public String deleteHealthRecord(@PathVariable("hid") Integer hid) {
        Integer num = healthRecordService.deleteHealthRecord(hid);
        if (num <= 0)
            return JsonResult.failed(-1,"删除康复记录信息失败");
        return JsonResult.success();
    }
}
