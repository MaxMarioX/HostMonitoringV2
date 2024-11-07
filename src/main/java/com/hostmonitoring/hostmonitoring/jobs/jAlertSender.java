package com.hostmonitoring.hostmonitoring.jobs;

import com.hostmonitoring.hostmonitoring.entity.Alert;
import com.hostmonitoring.hostmonitoring.repository.AlertRepository;
import com.hostmonitoring.hostmonitoring.service.Alert.AlertService;

import java.util.Optional;

public class jAlertSender {

    private AlertRepository alertRepository;
    private AlertService alertService;
    private Alert alert = null;

    public jAlertSender(AlertRepository alertRepository, AlertService alertService)
    {
        this.alertRepository = alertRepository;
        this.alertService = alertService;
    }

    private void checkAlert()
    {
        Optional<Alert> a = alertRepository.findById(1L);
        alert = a.orElse(null);

        if(alert != null)
        {
            if(alert.getActive())
            {
                alertService.send(alert.getMessage());
            }
        } else {
            //alert is null
        }
    }

}
