package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.entity.HostAvailability;
import com.hostmonitoring.hostmonitoring.repository.HostAvailabilityRepository;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import com.hostmonitoring.hostmonitoring.service.Ping.Ping;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

//Responsible for checking hosts
public class jHostsControl {

    private HostRepository hostRepository;
    private HostAvailabilityRepository hostAvailabilityRepository;
    private Map<Host,Boolean> hostBooleanMap = null;
    private List<Host> hostList = null;
    private Ping ping;

    public jHostsControl(HostRepository hostRepository, HostAvailabilityRepository hostAvailabilityRepository)
    {
        this.hostRepository = hostRepository;
        this.hostAvailabilityRepository = hostAvailabilityRepository;
        this.hostBooleanMap = new HashMap<>();
        this.ping = new Ping();
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

                if(ping.send(host.getIpAddress()))
                {
                    hostBooleanMap.put(host,true);
                } else {
                    hostBooleanMap.put(host,false);
                }
                saveDateTime(host);
            }

        }

        //return hostBooleanMap;
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
