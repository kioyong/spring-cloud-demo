package com.example.authorization.service;

import com.example.authorization.model.UserEntity;
import com.example.authorization.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(user -> new User(username, user.getPassword(), user.getAuthorities()))
            .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    @Override
    public UserEntity registerUser(UserEntity user) throws Exception {
        if (!checkPasswordLength(user.getPassword())) {
            throw new Exception("Invalid Password!");
        } else if (userRepository.findByUsername(user.getUsername().toLowerCase()).isPresent()) {
            throw new Exception("Username Already Exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<GrantedAuthority> authorities = new CopyOnWriteArraySet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        user.setAuthorities(authorities);
        return userRepository.save(user);
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= 4 &&
            password.length() <= 20;
    }
}
