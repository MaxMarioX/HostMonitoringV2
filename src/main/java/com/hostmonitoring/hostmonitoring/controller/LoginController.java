package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import com.hostmonitoring.hostmonitoring.service.Ping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
@AllArgsConstructor
public class LoginController {

    private HostRepository hostRepository;

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    @GetMapping("/dashboard")
    @ResponseBody
    public String dashboard() throws IOException, InterruptedException {
        Host host = hostRepository.findByName("Database");
        String ipHost = host.getIpAddress();

        Ping ping = new Ping();

        if (ping.send(ipHost))
            return "OK";
        else
            return "ERROR";

            //Thread.sleep(5);
    }
}
