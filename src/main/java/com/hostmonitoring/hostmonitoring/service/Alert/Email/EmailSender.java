package com.hostmonitoring.hostmonitoring.service.Alert.Email;

import com.hostmonitoring.hostmonitoring.entity.EmailConfig;
import com.hostmonitoring.hostmonitoring.repository.EmailRepository;
import com.hostmonitoring.hostmonitoring.service.Alert.AlertSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;
import java.util.Optional;

public class EmailSender implements AlertSender {

    private EmailConfig emailConfig;
    private EmailRepository emailRepository;
    private SimpleMailMessage simpleMailMessage;
    private JavaMailSenderImpl javaMailSenderImpl;
    private Properties properties;

    public EmailSender(String setFrom, String setTo, String setSubject)
    {
        this.simpleMailMessage.setFrom(setFrom);
        this.simpleMailMessage.setTo(setTo);
        this.simpleMailMessage.setSubject(setSubject);

        this.javaMailSenderImpl = new JavaMailSenderImpl();
        this.properties = javaMailSenderImpl.getJavaMailProperties();

    }
    private void setEmailConfig()
    {
        Optional<EmailConfig> ec = emailRepository.findById(1L);
        this.emailConfig = ec.orElse(null);
    }

    private void setEmailSender()
    {
        this.javaMailSenderImpl.setHost(emailConfig.getServername());
        this.javaMailSenderImpl.setUsername(emailConfig.getUsername());
        this.javaMailSenderImpl.setPassword(emailConfig.getPassword());
        this.javaMailSenderImpl.setPort(emailConfig.getPort());

        this.properties.put("mail.smtp.auth",emailConfig.getSmtpAuth());
        this.properties.put("mail.smtp.starttls.enable",emailConfig.getTls());

        this.javaMailSenderImpl.setJavaMailProperties(properties);
    }

    @Override
    public void send(String message) {
        this.simpleMailMessage.setText(message);

    }
}
