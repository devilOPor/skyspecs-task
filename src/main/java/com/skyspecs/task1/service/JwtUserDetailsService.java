package com.skyspecs.task1.service;

import com.skyspecs.task1.config.Encoder;
import com.skyspecs.task1.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    Encoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.skyspecs.task1.entity.User user = userService.findUserByEmail(username);
        if (user.getEmail().equals(username)) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for(Role role: user.getRoles()){
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            return new User(user.getEmail(), user.getPassword(),authorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}