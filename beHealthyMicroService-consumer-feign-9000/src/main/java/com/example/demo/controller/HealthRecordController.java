package com.example.demo.controller;

import com.example.demo.entity.HealthRecord;
import com.example.demo.service.HealthRecordService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/healthRecord")
public class HealthRecordController {
    @Autowired
    private HealthRecordService healthRecordService;

    @PostMapping("/getAll/{uid}")
    public String getAll(@PathVariable("uid") Integer uid) {
        return JsonResult.success(healthRecordService.getAll(uid));
    }

    @PostMapping("/findById/{hid}")
    public String findById(@PathVariable("hid") Integer hid) {
        HealthRecord healthRecord = healthRecordService.findById(hid);
        if (healthRecord == null)
            return JsonResult.failed(-1,"找不到该康复记录表");
        return JsonResult.success(healthRecord);
    }

    @PostMapping("/addHealthRecord")
    public String addHealthRecord(@RequestBody HealthRecord healthRecord) {
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        healthRecord.setOperTime(simpleDateFormat.format(date));
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        healthRecord.setDate(simpleDateFormat.format(date));
        Integer num = healthRecordService.addHealthRecord(healthRecord);
        if (num <= 0)
            return JsonResult.failed(-1,"添加康复记录信息失败");
        return JsonResult.success();
    }
    @PostMapping("/updateHealthRecord")
    public String  updateHealthRecord(@RequestBody HealthRecord healthRecord) {
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        healthRecord.setOperTime(simpleDateFormat.format(date));
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
