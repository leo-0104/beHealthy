package com.example.demo.controller;

import com.example.demo.entity.HealthNews;
import com.example.demo.service.HealthNewsService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthNews")
public class HealthNewsController {
    @Autowired
    private HealthNewsService healthNewsService;

    @GetMapping("/getAll")
    public String getAll() {
        return JsonResult.success(healthNewsService.getAll());
    }

    @GetMapping("/findById/{nid}")
    public String findById(@PathVariable("nid") Integer hid) {
        HealthNews  healthNews = healthNewsService.findById(hid);
        if (healthNews == null)
            return JsonResult.failed(-1,"找不到该健康资讯信息");
        return JsonResult.success(healthNews);
    }

    @PostMapping("/addHealthNews")
    public String addHealthNews(@RequestBody HealthNews healthNews) {
        Integer num = healthNewsService.addHealthNews(healthNews);
        if (num <= 0)
            return JsonResult.failed(-1,"添加健康资讯信息失败");
        return JsonResult.success();
    }
    @PostMapping("/updateHealthNews")
    public String  updateHealthNews(@RequestBody HealthNews HealthNews) {
        Integer num =  healthNewsService.updateHealthNews(HealthNews);
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
