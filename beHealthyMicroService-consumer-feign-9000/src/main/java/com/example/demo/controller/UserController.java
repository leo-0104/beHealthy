package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user){
         user = userService.login(user);
         if (user == null)
             return JsonResult.failed(-1,"找不到该用户");
        return JsonResult.success(user);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
          Integer num = userService.register(user);
          return JsonResult.success(num);
    }

    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") Integer id){
         User user =  userService.findById(id);
         return JsonResult.success(user);
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        Integer num =  userService.updateUser(user);
        if (num <= 0)
            return JsonResult.failed(1,"更新失败");
        return JsonResult.success(num);
    }

}
