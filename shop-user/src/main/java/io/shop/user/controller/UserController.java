package io.shop.user.controller;

import com.alibaba.fastjson.JSONObject;
import io.shop.bean.User;
import io.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{uid}")
    public User getUser(@PathVariable("uid") Long uid){
        System.out.println(uid);
        User user = userService.getUserById(uid);
        System.out.println(user.getUsername());
        log.info("获取到的用户信息为：{}", JSONObject.toJSONString(user));
        return user;
    }
}