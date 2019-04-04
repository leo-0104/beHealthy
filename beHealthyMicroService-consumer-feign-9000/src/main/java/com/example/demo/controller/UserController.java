package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


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
        //注册时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setRegisterTime(simpleDateFormat.format(date));
        //补全头像路径
        if (user.getProfile() != null){
            user.setProfile("E:\\image\\personPhoto\\" + user.getProfile());
        }
          Integer num = userService.register(user);
          if (num <= 0){
              return JsonResult.failed(1,"注册失败");
          }
          return JsonResult.success();

    }

    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") Integer id){
         User user =  userService.findById(id);
         if (user == null){
             return JsonResult.failed(-1,"该用户信息找不到");
         }
         return JsonResult.success(user);
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        Integer num =  userService.updateUser(user);
        if (num <= 0)
            return JsonResult.failed(1,"更新用户失败");
        return JsonResult.success(num);
    }

}
