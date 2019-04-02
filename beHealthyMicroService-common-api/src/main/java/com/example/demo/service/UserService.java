package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "microservicecloud-provider-app")
public interface UserService {
    @GetMapping("/user/findById/{id}")
    public User findById(@PathVariable("id") Integer id);

    @PostMapping("/user/login")
    public User login(@RequestBody User user);

    @PostMapping("/user/register")
    public Integer register(@RequestBody User user);

    @PostMapping("/user/updateUser")
    public Integer updateUser(@RequestBody User user);
}
