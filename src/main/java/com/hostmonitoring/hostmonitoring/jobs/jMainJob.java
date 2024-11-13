package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.repository.HostAvailabilityRepository;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

//Main job of HostMonitoring
public class jMainJob {

    private final HostRepository hostRepository;
    private HostAvailabilityRepository hostAvailabilityRepository;

    private jHostControl jHostControl;
    private jHostsPing jHostsPing;
    private jAlertSender jAlertSender;

    public jMainJob(HostRepository hostRepository, HostAvailabilityRepository hostAvailabilityRepository)
    {
        this.hostRepository = hostRepository;
        this.hostAvailabilityRepository = hostAvailabilityRepository;
    }

    public void startNow()
    {

    }

}
