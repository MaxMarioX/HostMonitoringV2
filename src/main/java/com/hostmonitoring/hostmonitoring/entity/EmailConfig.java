package com.hostmonitoring.hostmonitoring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

@Entity
@Table(name = "email_config")
@Getter
@Setter
public class EmailConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String servername;
    private String username;
    private String password;
    private Integer port;
    private Boolean smtpAuth;
    private Boolean tls;

    public StringEncryptor getPasswordEncryptor()
    {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword("@WS%TG");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
        return encryptor;
    }

    public void setUsername(String username) {
        this.username = getPasswordEncryptor().encrypt(username);
    }

    public String getUsername() {
        return getPasswordEncryptor().decrypt(username);
    }

    public void setPassword(String password)
    {
        this.password = getPasswordEncryptor().encrypt(password);
    }

    public String getPassword()
    {
        return getPasswordEncryptor().decrypt(password);
    }
}
