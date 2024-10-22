package com.hostmonitoring.hostmonitoring.service;

import com.hostmonitoring.hostmonitoring.entity.User;

public interface UserService {
    User findByUserName(String username);
    void saveUser(User user);
}
