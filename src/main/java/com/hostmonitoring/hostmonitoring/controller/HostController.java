package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.entity.HostAvailability;
import com.hostmonitoring.hostmonitoring.jobs.jHostsControl;
import com.hostmonitoring.hostmonitoring.repository.HostAvailabilityRepository;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class HostController {

    private HostRepository hostRepository;
    private HostAvailabilityRepository hostAvailabilityRepository;
    private jHostsControl jHostControl;

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
        host.setIpAddress("192.168.1.72");
        hostRepository.save(host);

        return "OK";
    }
    @GetMapping("/hostTime")
    public void hostTime() throws IOException {
        jHostControl = new jHostsControl(hostRepository, hostAvailabilityRepository);
        jHostControl.setHostsList();
        jHostControl.checkHosts();
    }

}
