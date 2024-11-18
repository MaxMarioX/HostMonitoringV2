package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

//Responsible for pinging hosts
public class jHostsPing {

    private HostRepository hostRepository;
    private List<Host> hostList = null;

    public jHostsPing(HostRepository hostRepository)
    {
        this.hostRepository = hostRepository;
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
        host.setLastDate(LocalDate.now());
        host.setLastTime(LocalTime.now());

        hostRepository.save(host);
    }
}
