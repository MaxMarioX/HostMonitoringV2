package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HostController {

    private HostRepository hostRepository;

    public HostController(HostRepository hostRepository)
    {
        this.hostRepository = hostRepository;
    }

    @GetMapping("/hostAdd")
    @ResponseBody
    public String hostAdd(Model model)
    {
        Host host = new Host();
        host.setName("Database");
        host.setIpAddress("192.168.0.7");
        hostRepository.save(host);

        return "OK";
    }

}
