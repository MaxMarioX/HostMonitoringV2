package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.repository.HostRepository;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//Main job of HostMonitoring
public class jMainJob {

    private HostRepository hostRepository;
    private jHostControl jHostControl;
    private jHostsPing jHostsPing;
    private jAlertSender jAlertSender;

    public jMainJob(HostRepository hostRepository)
    {
        this.hostRepository = hostRepository;
        this.jHostsPing = new jHostsPing(this.hostRepository);
    }

    public void startPing()
    {
        jHostsPing.setHostsList();

        while(true)
        {
            try {
                jHostsPing.checkHosts();
                TimeUnit.SECONDS.sleep(10);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
