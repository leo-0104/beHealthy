package com.example.demo.controller;

import com.example.demo.entity.MedicalAuscultation;
import com.example.demo.service.MedicalAuscultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalAus")
public class MedicalAuscultationController {
    @Autowired
    private MedicalAuscultationService medicalAuscultationService;

    @PostMapping("/getAll/{uid}")
    public List<MedicalAuscultation> getAll(@PathVariable("uid") Integer uid) {
        return medicalAuscultationService.getAll(uid);
    }

    @PostMapping("/findById/{mid}")
    public MedicalAuscultation findById(@PathVariable("mid") Integer mid) {
        return medicalAuscultationService.findById(mid);
    }

   @PostMapping("/addMedicalAus")
    public Integer addMedicalAus(@RequestBody MedicalAuscultation medicalAuscultation) {
        return medicalAuscultationService.addMedicalAus(medicalAuscultation);
    }


    @PostMapping("/updateMedicalAus")
    public Integer updateMedicalAus(@RequestBody MedicalAuscultation medicalAuscultation) {
        return medicalAuscultationService.updateMedicalAus(medicalAuscultation);
    }


    @PostMapping("/deleteMedicalAus/{mid}/{fileName}")
    public Integer deleteMedicalAus(@PathVariable("mid") Integer mid,@PathVariable("fileName")String fileName) {
        return medicalAuscultationService.deleteMedicalAus(mid);
    }
}
