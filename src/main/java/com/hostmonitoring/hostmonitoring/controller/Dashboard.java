package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import com.hostmonitoring.hostmonitoring.service.Ping.Ping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class Dashboard {

    private HostRepository hostRepository;

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
    }

}
