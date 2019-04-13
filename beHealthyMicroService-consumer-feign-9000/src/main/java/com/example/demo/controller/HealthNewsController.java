package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.HealthNews;
import com.example.demo.service.HealthNewsService;
import com.example.demo.service.SendService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/healthNews")
public class HealthNewsController {
    @Autowired
    private HealthNewsService healthNewsService;


    @GetMapping("/getAll")
    public String getAll() {
        List<HealthNews> list = healthNewsService.getAll();
        JsonResult jsonResult = new JsonResult(0, "解析成功", list,list.size());
        return JSON.toJSONString(jsonResult);
    }

    @PostMapping("/getAllByCondition")
    public String getAllByCondition() {
        List<HealthNews> list = healthNewsService.getAllByCondition();
        JsonResult jsonResult = new JsonResult(0, "解析成功", list,list.size());
        return JSON.toJSONString(jsonResult);
    }

    @PostMapping("/findById/{nid}")
    public String findById(@PathVariable("nid") Integer hid) {
        HealthNews  healthNews = healthNewsService.findById(hid);
        if (healthNews == null)
            return JsonResult.failed(-1,"找不到该健康资讯信息");
        return JsonResult.success(healthNews);
    }

    @PostMapping("/addHealthNews")
    public String addHealthNews(@RequestBody HealthNews healthNews) {
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        healthNews.setOperTime(simpleDateFormat.format(date));
        Integer num = healthNewsService.addHealthNews(healthNews);
        if (num <= 0)
            return JsonResult.failed(-1,"添加健康资讯信息失败");
        return JsonResult.success();
    }
    @PostMapping("/updateHealthNews")
    public String  updateHealthNews(@RequestBody HealthNews healthNews) {
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        healthNews.setOperTime(simpleDateFormat.format(date));
        Integer num =  healthNewsService.updateHealthNews(healthNews);
        if (num <= 0)
            return JsonResult.failed(-1,"更新健康资讯信息失败");
        return JsonResult.success();
    }
    @PostMapping("/deleteHealthNews/{nid}")
    public String deleteHealthNews(@PathVariable("nid") Integer nid) {
        Integer num = healthNewsService.deleteHealthNews(nid);
        if (num <= 0)
            return JsonResult.failed(-1,"删除健康资讯信息失败");
        return JsonResult.success();
    }


}
