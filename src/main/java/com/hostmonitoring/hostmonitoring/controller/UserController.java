package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.User;
import com.hostmonitoring.hostmonitoring.service.UserLogon.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @GetMapping("/add")
    @ResponseBody
    public String addUser()
    {
        User user = new User();
        user.setName("Mariusz");
        user.setPassword("1qaz2wsx");
        userService.saveUser(user);

        return "OK";
    }
}
