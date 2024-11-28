package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.loading.LoadEmailSettings;
import com.hostmonitoring.hostmonitoring.repository.EmailRepository;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import com.hostmonitoring.hostmonitoring.service.Alert.Email.EmailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Main job of HostMonitoring
public class jMainJob {

    private List<Host> hostList = null;
    private HostRepository hostRepository;
    private EmailRepository emailRepository;
    private jHostControl jHostControl;
    private jHostsPing jHostsPing;
    private EmailSender emailSender;
    private LoadEmailSettings loadSettings;

    public jMainJob(HostRepository hostRepository, EmailRepository emailRepository)
    {
        this.hostRepository = hostRepository;
        this.emailRepository = emailRepository;
    }
    public void prepareMainJob()
    {
        this.jHostsPing = new jHostsPing();
        this.jHostControl = new jHostControl();
        this.hostList = hostRepository.findAll();
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
                       // emailSender.send("Host: " + host.getName() + " doesn't work!");
                    }
                }

                TimeUnit.SECONDS.sleep(10);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
