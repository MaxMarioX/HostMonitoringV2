package com.hostmonitoring.hostmonitoring.service.UserLogon;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private com.hostmonitoring.hostmonitoring.entity.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities, com.hostmonitoring.hostmonitoring.entity.User user)
    {
        super(username, password, authorities);
        this.user = user;
    }

    public com.hostmonitoring.hostmonitoring.entity.User getUser()
    {
        return user;
    }
}