package com.skyspecs.task1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encoder {
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
