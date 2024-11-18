package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.jobs.jHostsPing;
import com.hostmonitoring.hostmonitoring.jobs.jMainJob;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class HostController {

    private HostRepository hostRepository;
    private jHostsPing jHostControl;

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

        host.setDefaultDate();
        host.setDefaultTime();

        hostRepository.save(host);

        return "OK";
    }

    @GetMapping("/hostcheck")
    @ResponseBody
    public String hostCheck() throws IOException {
        /*
        Optional<Host> host = hostRepository.findById(2L);
        jHostControl = new jHostsPing(hostRepository, hostAvailabilityRepository);

        if(jHostControl.checkDateTime(host.get(),50))
        {
            return "true";
        } else {
            return "false";
        }
        */

        jMainJob jMainJob = new jMainJob(hostRepository);
        jMainJob.loadSettings();
        jMainJob.startPing();

        return "true";
    }
}
