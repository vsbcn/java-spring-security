package com.ironhack.security.services.impl;

import com.ironhack.security.models.User;
import com.ironhack.security.repositories.UserRepository;
import com.ironhack.security.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User doesn't exist");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(userOptional.get());

        return customUserDetails;
    }
}
