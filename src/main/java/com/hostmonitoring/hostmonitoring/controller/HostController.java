package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.entity.HostAvailability;
import com.hostmonitoring.hostmonitoring.jobs.jHostsPing;
import com.hostmonitoring.hostmonitoring.repository.HostAvailabilityRepository;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Optional;

@Controller
public class HostController {

    private HostRepository hostRepository;
    private HostAvailabilityRepository hostAvailabilityRepository;
    private jHostsPing jHostControl;

    public HostController(HostRepository hostRepository, HostAvailabilityRepository hostAvailabilityRepository)
    {
        this.hostRepository = hostRepository;
        this.hostAvailabilityRepository = hostAvailabilityRepository;
    }

    @GetMapping("/hostAdd")
    @ResponseBody
    public String hostAdd(Model model)
    {
        Host host = new Host();
        HostAvailability hostAvailability = new HostAvailability();

        host.setName("Database");
        host.setIpAddress("192.168.1.72");

        hostAvailability.setDefaultDate();
        hostAvailability.setDefaultTime();
        hostAvailability.setHost(host);

        hostRepository.save(host);
        hostAvailabilityRepository.save(hostAvailability);

        return "OK";
    }
    @GetMapping("/hostTime")
    public void hostTime() throws IOException {
        jHostControl = new jHostsPing(hostRepository, hostAvailabilityRepository);
        jHostControl.setHostsList();
        jHostControl.checkHosts();
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

        return "true";
    }
}
