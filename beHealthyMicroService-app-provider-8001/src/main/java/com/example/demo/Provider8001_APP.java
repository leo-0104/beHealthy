package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.CaseHistory;
import com.example.demo.service.CaseHistoryService;
import com.example.demo.service.ReceiverService;
import com.example.demo.service.impl.CaseHistoryEServiceImpl;
import com.example.demo.service.impl.CaseHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

import java.time.Duration;


@SpringBootApplication
@EnableEurekaClient    //表明这是一个eureka客户端，向eureka服务端注册服务
@EnableBinding(ReceiverService.class)
@EnableCaching
public class Provider8001_APP {
    @Autowired
    private ReceiverService receiverService;
    @Autowired
    private CaseHistoryServiceImpl caseHistoryService;
    @Autowired
    private CaseHistoryEServiceImpl caseHistoryEService;

    public static void main(String[] args) {
        SpringApplication.run(Provider8001_APP.class,args);
    }


    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(300));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }

    @StreamListener("myInput")
    public void onReceive(Message<String> message){
        String key = new String((byte[]) message.getHeaders().get("messageKey"));
        String msg = message.getPayload();
        CaseHistory caseHistory = null;
        //添加病历信息
        if (key.equals("addCaseHistory")){
            caseHistory = JSONObject.parseObject(msg, CaseHistory.class);
            //持久化到MySQL以及ES中
            caseHistoryService.addCaseHis(caseHistory);
            caseHistoryEService.addCaseHis(caseHistory);
        }else if (key.equals("updateCaseHistory")){    //更新病历信息
            caseHistory = JSONObject.parseObject(msg, CaseHistory.class);
            //更新MySQL以及ES中的病历信息
            caseHistoryService.updateCaseHis(caseHistory);
            caseHistoryEService.updateCaseHis(caseHistory);
        }else if(key.equals("deleteCaseHistory")){     //删除病历信息
            //删除MySQL以及ES中对应的病历信息
            caseHistoryService.deleteCaseHis(Integer.valueOf(msg));
            caseHistoryEService.deleteCaseHis(Integer.valueOf(msg));
        }

    }
}
