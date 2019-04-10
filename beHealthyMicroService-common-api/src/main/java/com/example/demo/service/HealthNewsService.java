package com.example.demo.service;

import com.example.demo.entity.HealthNews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "microservicecloud-provider-app")
public interface HealthNewsService {
    @GetMapping("/healthNews/getAll")
    public List<HealthNews> getAll() ;
    @PostMapping("/healthNews/getAllByCondition")
    public List<HealthNews> getAllByCondition();
    @PostMapping("/healthNews/findById/{nid}")
    public HealthNews findById(@PathVariable("nid") Integer nid);

    @PostMapping("/healthNews/addHealthNews")
    public Integer addHealthNews(@RequestBody HealthNews healthNews);
    @PostMapping("/healthNews/updateHealthNews")
    public Integer updateHealthNews(@RequestBody HealthNews healthNews);
    @PostMapping("/healthNews/deleteHealthNews/{nid}")
    public Integer deleteHealthNews(@PathVariable("nid") Integer nid);
}
