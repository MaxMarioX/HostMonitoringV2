package com.hostmonitoring.hostmonitoring.loading;

import com.hostmonitoring.hostmonitoring.entity.EmailConfig;
import com.hostmonitoring.hostmonitoring.repository.EmailRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Optional;
import java.util.Properties;

@Configuration
public class LoadEmailSettings {
    private EmailRepository emailRepository;
    private JavaMailSenderImpl javaMailSenderImpl;
    private EmailConfig emailConfig;
    private Properties properties;

    public LoadEmailSettings(EmailRepository emailRepository)
    {
        this.emailRepository = emailRepository;
        this.javaMailSenderImpl = new JavaMailSenderImpl();
        this.properties = javaMailSenderImpl.getJavaMailProperties();
    }

    @Bean
    public JavaMailSenderImpl loadEmailConfiguration()
    {
        Optional<EmailConfig> ec = emailRepository.findFirstById("1");
        this.emailConfig = ec.orElse(null);

        this.properties = javaMailSenderImpl.getJavaMailProperties();

        this.javaMailSenderImpl.setHost(emailConfig.getServername());
        this.javaMailSenderImpl.setUsername(emailConfig.getUsername());
        this.javaMailSenderImpl.setPassword(emailConfig.getPassword());
        this.javaMailSenderImpl.setPort(emailConfig.getPort());

        this.properties.put("mail.smtp.auth",emailConfig.getSmtpAuth());
        this.properties.put("mail.smtp.starttls.enable",emailConfig.getTls());

        this.javaMailSenderImpl.setJavaMailProperties(properties);

        return this.javaMailSenderImpl;
    }
}
