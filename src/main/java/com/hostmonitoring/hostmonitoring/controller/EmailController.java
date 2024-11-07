package com.hostmonitoring.hostmonitoring.controller;

import com.hostmonitoring.hostmonitoring.entity.EmailConfig;
import com.hostmonitoring.hostmonitoring.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class EmailController {

    private EmailRepository emailRepository;

    @GetMapping("/addEmailSettings")
    @ResponseBody
    private String addEmailSettings()
    {
        EmailConfig emailConfig = new EmailConfig();
        emailConfig.setServername("smtp.cal.pl");
        emailConfig.setUsername("alert@mp-programs.pl");
        emailConfig.setPassword("mez8L2xebZs22NCqMvkn");
        emailConfig.setTls(true);
        emailConfig.setSmtpAuth(true);
        emailConfig.setPort(587);
        emailRepository.save(emailConfig);

        return "Added";
    }

}
