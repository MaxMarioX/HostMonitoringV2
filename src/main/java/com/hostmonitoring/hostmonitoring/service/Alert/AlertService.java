package com.hostmonitoring.hostmonitoring.service.Alert;

public class AlertService {

    private AlertSender alertSender;

    public AlertService(AlertSender alertSender)
    {
        this.alertSender = alertSender;
    }

    public void send(String message)
    {
        this.alertSender.send(message);
    }
}
