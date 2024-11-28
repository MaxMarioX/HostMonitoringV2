package com.hostmonitoring.hostmonitoring.service.Alert.Email;
import com.hostmonitoring.hostmonitoring.entity.EmailConfig;
import com.hostmonitoring.hostmonitoring.loading.LoadEmailSettings;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private SimpleMailMessage simpleMailMessage;
    private LoadEmailSettings loadEmailSettings;
    private JavaMailSenderImpl javaMailSender;

    public EmailSender(LoadEmailSettings loadEmailSettings) {
        this.simpleMailMessage = new SimpleMailMessage();
        this.loadEmailSettings = loadEmailSettings;
    }

    public void prepareSender(String setFrom, String setTo, String setSubject)
    {
        this.simpleMailMessage.setFrom(setFrom);
        this.simpleMailMessage.setTo(setTo);
        this.simpleMailMessage.setSubject(setSubject);

        loadEmailSettings.loadEmailConfiguration();
        javaMailSender = loadEmailSettings.loadEmailConfiguration();
    }

    public void send(String message) {
        this.simpleMailMessage.setText(message);
        this.javaMailSender.send(simpleMailMessage);
    }
}
