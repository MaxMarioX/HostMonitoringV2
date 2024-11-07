package com.hostmonitoring.hostmonitoring.service.Ping;

import java.io.IOException;
import java.net.InetAddress;

public class Ping {

    public Boolean send(String addressIpv4) throws IOException
    {
        return InetAddress.getByName(addressIpv4).isReachable(5);
    }
}
