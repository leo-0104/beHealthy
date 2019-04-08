package com.example.demo.controller;

import com.example.demo.entity.MedicalAuscultation;
import com.example.demo.service.MedicalAuscultationService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/medicalAus")
public class MedicalAuscultationController {
    @Autowired
    private MedicalAuscultationService medicalAuscultationService;

    @PostMapping("/getAll/{uid}")
    public String getAll(@PathVariable("uid") Integer uid) {
        return JsonResult.success(medicalAuscultationService.getAll(uid));
    }

    @PostMapping("/findById/{mid}")
    public String findById(@PathVariable("mid") Integer mid) {
        MedicalAuscultation medicalAuscultation = medicalAuscultationService.findById(mid);
        if (medicalAuscultation == null){
            return JsonResult.failed(-1,"该就医听诊信息不存在");
        }
        return JsonResult.success(medicalAuscultation);
    }

    @PostMapping("/addMedicalAus")
    public String addMedicalAus(@RequestBody MedicalAuscultation medicalAuscultation) {
        Integer num =  medicalAuscultationService.addMedicalAus(medicalAuscultation);
        if (num <= 0){
            return JsonResult.failed(-1,"添加就医听诊信息失败");
        }
        return JsonResult.success();
    }


    @PostMapping("/updateMedicalAus")
    public String updateMedicalAus(@RequestBody MedicalAuscultation medicalAuscultation) {
        //注册时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        medicalAuscultation.setOperTime(simpleDateFormat.format(date));
        Integer num =  medicalAuscultationService.updateMedicalAus(medicalAuscultation);
        if (num <= 0){
            return JsonResult.failed(-1,"更新就医听诊信息失败");
        }
        return JsonResult.success();
    }


    @PostMapping("/deleteMedicalAus/{mid}/{fileName}")
    public String deleteMedicalAus(@PathVariable("mid") Integer mid,@PathVariable("fileName")String fileName) {
        Integer num =  medicalAuscultationService.deleteMedicalAus(mid,fileName);
        if (num <= 0){
            return JsonResult.failed(-1,"删除就医听诊信息失败");
        }
        //删除文件
        fileName = "E:\\record\\" + fileName;
        File file = new File(fileName);
        if (file.exists()){
            file.delete();
        }
        return JsonResult.success();
    }
}
