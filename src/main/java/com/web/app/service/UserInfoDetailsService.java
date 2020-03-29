package com.web.app.service;

import com.web.app.dto.UserInfo;
import com.web.app.repository.UserInfoJpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserInfoDetailsService implements UserDetailsService {
    private final UserInfoJpaRepository userInfoJpaRepository;

    public UserInfoDetailsService(UserInfoJpaRepository userInfoJpaRepository) {
        this.userInfoJpaRepository = userInfoJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserInfo user = userInfoJpaRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "Opps! user not found with user-name: " + username);
        }
        return new User(
                user.getUsername(), user.getPassword(),
                Collections.singleton(getAuthorities(user)));
    }
    private SimpleGrantedAuthority getAuthorities(UserInfo user) {
        return new SimpleGrantedAuthority("ROLE_" + user.getRole());
    }
}