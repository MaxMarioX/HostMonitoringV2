package com.hostmonitoring.hostmonitoring.service.Host;

import com.hostmonitoring.hostmonitoring.entity.Host;
import com.hostmonitoring.hostmonitoring.repository.HostRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class HostService {

    private List<Host> hostList;
    private HostRepository hostRepository;

    private List<Host> getHostList()
    {
        this.hostList = new ArrayList<>();
        return hostRepository.findAll();
    }
}
