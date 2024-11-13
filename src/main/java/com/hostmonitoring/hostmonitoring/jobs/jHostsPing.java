package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.entity.HostAvailability;
import com.hostmonitoring.hostmonitoring.repository.HostAvailabilityRepository;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

//Responsible for pinging hosts
public class jHostsPing {

    private HostRepository hostRepository;
    private HostAvailabilityRepository hostAvailabilityRepository;
    private List<Host> hostList = null;

    public jHostsPing(HostRepository hostRepository, HostAvailabilityRepository hostAvailabilityRepository)
    {
        this.hostRepository = hostRepository;
        this.hostAvailabilityRepository = hostAvailabilityRepository;
    }

    //Get all hosts from database to List
    public void setHostsList()
    {
        this.hostList = hostRepository.findAll();
    }

    //Send ping to each host and save date and time checking
    public void checkHosts() throws IOException {
        if(hostList != null)
        {
            for(Iterator<Host> hostIterator = hostList.iterator(); hostIterator.hasNext();)
            {
                Host host = hostIterator.next();

                if(send(host.getIpAddress()))
                {
                    saveDateTime(host);   //save to database
                }
            }
        }
    }

    private Boolean send(String addressIpv4) throws IOException
    {
        return InetAddress.getByName(addressIpv4).isReachable(5);
    }

    //Save date and time checking to database
    private void saveDateTime(Host host)
    {
        HostAvailability hostAvailability = new HostAvailability();

        hostAvailability.setLastDate(LocalDate.now());
        hostAvailability.setLastTime(LocalTime.now());
        hostAvailability.setHost(host);

        hostAvailabilityRepository.save(hostAvailability);
    }
}
