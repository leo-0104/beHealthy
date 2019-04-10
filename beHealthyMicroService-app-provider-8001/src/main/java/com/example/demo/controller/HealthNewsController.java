package com.example.demo.controller;

import com.example.demo.entity.HealthNews;
import com.example.demo.service.HealthNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthNews")
public class HealthNewsController {
    @Autowired
    private HealthNewsService healthNewsService;
    @GetMapping("/getAll")
    public List<HealthNews> getAll() {
        return healthNewsService.getAll();
    }

    @PostMapping("/getAllByCondition")
    public List<HealthNews> getAllByCondition() {
        return healthNewsService.getAllByCondition();
    }

    @PostMapping("/findById/{nid}")
    public HealthNews findById(@PathVariable("nid") Integer nid) {
        return healthNewsService.findById(nid);
    }

    @PostMapping("/addHealthNews")
    public Integer addHealthNews(@RequestBody HealthNews healthNews) {
        return healthNewsService.addHealthNews(healthNews);
    }
    @PostMapping("/updateHealthNews")
    public Integer updateHealthNews(@RequestBody HealthNews healthNews) {
        return healthNewsService.updateHealthNews(healthNews);
    }
    @PostMapping("/deleteHealthNews/{nid}")
    public Integer deleteHealthNews(@PathVariable Integer nid) {
        return healthNewsService.deleteHealthNews(nid);
    }
}
