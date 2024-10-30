package com.hostmonitoring.hostmonitoring.service.UserLogon;

import com.hostmonitoring.hostmonitoring.entity.Role;
import com.hostmonitoring.hostmonitoring.entity.User;
import com.hostmonitoring.hostmonitoring.repository.RoleRepository;
import com.hostmonitoring.hostmonitoring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(new ArrayList<>(Collections.singletonList(role)));
        userRepository.save(user);
    }
}
