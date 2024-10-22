package com.hostmonitoring.hostmonitoring.service;

import com.hostmonitoring.hostmonitoring.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SpringDataUserDetalisService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        return new CurrentUser(user.getName(), user.getPassword(), getAuthorities(user), user);
    }

    private List<GrantedAuthority> getAuthorities(User user)
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }
}
