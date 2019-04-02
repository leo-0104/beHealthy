package com.example.demo.service;

import com.example.demo.entity.MedicalAuscultation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "microservicecloud-provider-app")
public interface MedicalAuscultationService {
    @GetMapping("/medicalAus/getAll/{uid}")
    public List<MedicalAuscultation> getAll(@PathVariable("uid") Integer uid) ;

    @GetMapping("/medicalAus/findById/{mid}")
    public MedicalAuscultation findById(@PathVariable("mid") Integer mid);

    @PostMapping("/medicalAus/addMedicalAus")
    public Integer addMedicalAus(@RequestBody MedicalAuscultation medicalAuscultation) ;


    @PostMapping("/medicalAus/updateMedicalAus")
    public Integer updateMedicalAus(@RequestBody MedicalAuscultation medicalAuscultation);


    @PostMapping("/medicalAus/deleteMedicalAus/{mid}")
    public Integer deleteMedicalAus(@PathVariable("mid") Integer mid);
}
