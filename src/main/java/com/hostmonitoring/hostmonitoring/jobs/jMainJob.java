package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

//Main job of HostMonitoring
public class jMainJob {

    private List<Host> hostList = null;

    private HostRepository hostRepository;
    private jHostControl jHostControl;
    private jHostsPing jHostsPing;
    private jAlertSender jAlertSender;

    public jMainJob(HostRepository hostRepository)
    {
        this.hostRepository = hostRepository;
        this.jHostsPing = new jHostsPing();
        this.jHostControl = new jHostControl();
    }

    public void startPing()
    {
        jHostsPing.getHostList(hostList);

        while(true)
        {
            try {
                hostList = jHostsPing.checkHosts();

                for(Host host : hostList)
                {
                    if(jHostControl.checkDateTime(host,5))
                    {
                        //send alarm
                    }
                }

                TimeUnit.SECONDS.sleep(10);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadSettings()
    {
        this.hostList = hostRepository.findAll();
    }

    private void saveResults()
    {
        hostRepository.saveAll(hostList);
    }
}
