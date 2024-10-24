package com.hostmonitoring.hostmonitoring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard()
    {
        return "dashboard";
    }

}
