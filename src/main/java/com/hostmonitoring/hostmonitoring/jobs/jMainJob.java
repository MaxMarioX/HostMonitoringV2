package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.repository.HostAvailabilityRepository;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

//Main job of HostMonitoring
public class jMainJob {

    private HostRepository hostRepository;
    private HostAvailabilityRepository hostAvailabilityRepository;
    private jHostControl jHostControl;
    private jHostsPing jHostsPing;
    private jAlertSender jAlertSender;

    public jMainJob(HostRepository hostRepository, HostAvailabilityRepository hostAvailabilityRepository)
    {
        this.hostRepository = hostRepository;
        this.hostAvailabilityRepository = hostAvailabilityRepository;

        this.jHostsPing = new jHostsPing(this.hostRepository,this.hostAvailabilityRepository);
    }

    public void startPing()
    {
        while(true)
        {
            try {
                jHostsPing.checkHosts();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
