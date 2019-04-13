package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.CaseHistory;
import com.example.demo.service.CaseHistoryService;
import com.example.demo.service.SendService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/caseHistory")
public class CaseHistoryController {
    @Autowired
    private CaseHistoryService caseHistoryService;
    @Autowired
    private SendService sendService;
    @PostMapping("/getAll/{uid}/{fid}")
    public String getAll(@PathVariable("uid")Integer uid, @PathVariable("fid")Integer fid){
        return JsonResult.success(caseHistoryService.getAll(uid,fid));
    }

    @PostMapping("/getAllByCondition/{uid}/{fid}")
    public String getAllByCondition(@PathVariable("uid")Integer uid, @PathVariable("fid")Integer fid, @PathParam("query") String query){
        List<CaseHistory> list = caseHistoryService.getAllByCondition(uid,fid,query);
        return JsonResult.success(list);
    }

    @PostMapping("/findById/{cid}")
    public String findById(@PathVariable("cid")Integer cid){
        CaseHistory caseHistory =  caseHistoryService.findById(cid);
        if (caseHistory == null)
            return JsonResult.failed(-1,"查询不到该病历");
        return JsonResult.success(caseHistory);
    }

    @PostMapping("/addCaseHistory")
    public String addCaseHistory(@RequestBody CaseHistory caseHistory){
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        caseHistory.setOperTime(simpleDateFormat.format(date));
        //补全头像路径
        if (caseHistory.getPhoto() != null){
            caseHistory.setPhoto("E:\\image\\medicalRecordPhoto\\" + caseHistory.getPhoto());
        }
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(caseHistory);
        boolean flag = sendMsg("addCaseHistory",jsonObject.toJSONString());
        if (!flag){
            return JsonResult.failed(-1,"保存病历信息失败");
        }
        return JsonResult.success();
    }

    @PostMapping("/updateCaseHistory")
    public String updateCaseHistory(@RequestBody CaseHistory caseHistory){
        //操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        caseHistory.setOperTime(simpleDateFormat.format(date));
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(caseHistory);
        boolean flag = sendMsg("updateCaseHistory",jsonObject.toJSONString());
        if (!flag){
            return JsonResult.failed(-1,"更新病历信息失败");
        }
        return JsonResult.success();
    }

    @PostMapping("/deleteCaseHistory/{cid}")
    public String deleteCaseHistory(@PathVariable("cid")Integer cid){
        boolean flag = sendMsg("deleteCaseHistory",String.valueOf(cid));
        if (!flag){
            return JsonResult.failed(-1,"删除病历信息失败");
        }
        return JsonResult.success();
    }

   //发送消息
    public boolean sendMsg(String key,String msg){
        Message message = MessageBuilder.withPayload(msg.getBytes()).setHeader("messageKey",key.getBytes()).build();
        return sendService.sendMsg().send(message);
    }
}
