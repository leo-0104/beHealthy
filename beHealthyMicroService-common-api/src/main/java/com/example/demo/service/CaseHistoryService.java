package com.example.demo.service;

import com.example.demo.entity.CaseHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "microservicecloud-provider-app")
public interface CaseHistoryService {

    @GetMapping("/caseHistory/getAll/{id}/{flag}")
    public List<CaseHistory> getAll(@PathVariable("id")Integer id, @PathVariable("flag")Boolean flag);

    @GetMapping("/caseHistory/findById/{cid}")
    public CaseHistory findById(@PathVariable("cid")Integer cid);

    @PostMapping("/caseHistory/addCaseHistory")
    public Integer addCaseHistory(@RequestBody CaseHistory caseHistory);

    @PostMapping("/caseHistory/updateCaseHistory")
    public Integer updateCaseHistory(@RequestBody CaseHistory caseHistory);

    @PostMapping("/caseHistory/deleteCaseHistory/{cid}")
    public Integer deleteCaseHistory(@PathVariable("cid")Integer cid);
}
