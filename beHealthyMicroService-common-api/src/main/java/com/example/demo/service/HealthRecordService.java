package com.example.demo.service;

import com.example.demo.entity.HealthRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(value = "microservicecloud-provider-app")
public interface HealthRecordService {
    @GetMapping("/healthRecord/getAll/{uid}")
    public List<HealthRecord> getAll(@PathVariable("uid") Integer uid);

    @GetMapping("/healthRecord/findById/{hid}")
    public HealthRecord findById(@PathVariable("hid") Integer hid) ;

    @PostMapping("/healthRecord/addHealthRecord")
    public Integer addHealthRecord(@RequestBody HealthRecord healthRecord);
    @PostMapping("/healthRecord/updateHealthRecord")
    public Integer updateHealthRecord(@RequestBody HealthRecord healthRecord) ;
    @PostMapping("/healthRecord/deleteHealthRecord/{hid}")
    public Integer deleteHealthRecord(@PathVariable("hid") Integer hid);
}
