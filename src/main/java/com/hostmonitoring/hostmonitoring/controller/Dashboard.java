package com.hostmonitoring.hostmonitoring.controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class Dashboard {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

}
