package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.User;
import com.hostmonitoring.hostmonitoring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class Login {

    PasswordEncoder passwordEncoder;
    UserService userService;

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

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

    @GetMapping("/dashboard")
    public String dashboard()
    {
        return "dashboard";
    }

}
