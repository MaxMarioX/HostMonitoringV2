package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

//Responsible for pinging hosts
public class jHostsPing {

    private List<Host> hostList = null;

    public void getHostList(List<Host> hostList)
    {
        this.hostList = hostList;
    }

    //Send ping to each host and save date and time checking
    public List<Host> checkHosts() throws IOException {
        if(hostList != null)
        {
            for(Host host : hostList)
            {
                if(send(host.getIpAddress()))
                {
                    host.setLastDate(LocalDate.now());
                    host.setLastTime(LocalTime.now());
                }
            }
        }
        return hostList;
    }

    private Boolean send(String addressIpv4) throws IOException
    {
        return InetAddress.getByName(addressIpv4).isReachable(5);
    }
}
