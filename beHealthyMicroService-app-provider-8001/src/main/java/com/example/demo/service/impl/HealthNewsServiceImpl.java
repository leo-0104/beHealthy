package com.example.demo.service.impl;

import com.example.demo.dao.HealthNewsDao;
import com.example.demo.entity.HealthNews;
import com.example.demo.service.HealthNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class HealthNewsServiceImpl implements HealthNewsService {
    @Autowired
    private HealthNewsDao healthNewsDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<HealthNews> getAll() {
        return healthNewsDao.getAll();
    }

    @Override
    public List<HealthNews> getAllByCondition() {
        List<HealthNews> list = null;
        //从缓存中查询数据，没有则从MySQL中查询
        if (redisTemplate.opsForValue().get("getAll") != null){
            list = (List<HealthNews>) redisTemplate.opsForValue().get("getAll");
        }else {
            list= healthNewsDao.getAllByCondition();
            redisTemplate.opsForValue().set("getAll",list);
            //缓存5分钟
            redisTemplate.expire("getAll",5 * 60, TimeUnit.SECONDS);
        }
        return list;
    }

    @Override
    public HealthNews findById(Integer nid) {
        HealthNews healthNews = null;
        //从缓存中查询数据，没有则从MySQL中查询
        if (redisTemplate.opsForValue().get("healthNews_" + nid) != null){
            healthNews = (HealthNews) redisTemplate.opsForValue().get("healthNews_" + nid);
        }else {
            healthNews= healthNewsDao.findById(nid);
            redisTemplate.opsForValue().set("healthNews_" + nid ,healthNews);
            //缓存5分钟
            redisTemplate.expire("healthNews_" + nid,5 * 60, TimeUnit.SECONDS);
        }
        return healthNews;
    }

    @Override
    public Integer addHealthNews(HealthNews healthNews) {
        int num =  healthNewsDao.addHealthNews(healthNews);
        if (num > 0){
            //更新缓存
            findById(healthNews.getNid());
            redisTemplate.delete("getAll");
            getAllByCondition();
        }
        return num;
    }

    @Override
    public Integer updateHealthNews(HealthNews healthNews) {
        int num =  healthNewsDao.updateHealthNews(healthNews);
        if (num > 0){
            //更新缓存
            redisTemplate.delete("healthNews_" + healthNews.getNid());
            findById(healthNews.getNid());
            redisTemplate.delete("getAll");
            getAllByCondition();
        }
        return num;
    }

    @Override
    public Integer deleteHealthNews(Integer nid) {
        int num =  healthNewsDao.deleteHealthNews(nid);
        if (num > 0){
            //更新缓存
            redisTemplate.delete("healthNews_" + nid);
            redisTemplate.delete("getAll");
            getAllByCondition();
        }
        return num;
    }
}
