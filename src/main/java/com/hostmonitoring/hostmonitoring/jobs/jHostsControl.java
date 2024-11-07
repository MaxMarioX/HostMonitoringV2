package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import com.hostmonitoring.hostmonitoring.service.Ping.Ping;

import java.io.IOException;
import java.util.*;

public class jHostsControl {

    private HostRepository hostRepository;
    private Map<Host,Boolean> hostBooleanMap = null;
    private List<Host> hostList = null;
    private Ping ping;

    public jHostsControl(HostRepository hostRepository)
    {
        this.hostRepository = hostRepository;
        this.hostBooleanMap = new HashMap<>();
        this.ping = new Ping();
    }

    private void setHostsList()
    {
        this.hostList = hostRepository.findAll();
    }

    private Map<Host,Boolean> checkHosts() throws IOException {
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
            }

        }

        return hostBooleanMap;
    }
}
