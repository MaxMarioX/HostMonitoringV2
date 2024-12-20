package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Host;

import java.time.LocalDate;
import java.time.LocalTime;

//Responsible for control hosts
public class jHostControl {

    //Check last date and time received ping and return true when host isn't available more than t_diff
    public boolean checkDateTime(Host host, long t_diff)
    {
        LocalDate date = host.getLastDate();
        LocalTime time = host.getLastTime();

        LocalDate date_now = LocalDate.now();
        LocalTime time_now = LocalTime.now();

        long ct_diff;

        if(date.isAfter(date_now))
            return false;

        ct_diff = ((time_now.getHour() - time.getHour()) * 60) + (time_now.getMinute() - time.getMinute());
        System.out.println(ct_diff);

        if(ct_diff > t_diff)
            return true;
        else
            return false;
    }
}
