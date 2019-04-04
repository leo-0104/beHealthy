package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        return userService.findById(id);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/register")
    public Integer register(@RequestBody User user){
        userService.register(user);
        return user.getUid();
    }

    @PostMapping("/updateUser")
    public Integer updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
