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
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;


@SpringBootApplication
@EnableEurekaClient    //表明这是一个eureka客户端，向eureka服务端注册服务
@EnableBinding(ReceiverService.class)
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
