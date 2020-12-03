package com.xzit.project.security;

import com.xzit.project.domain.Authority;
import com.xzit.project.domain.User;
import com.xzit.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository.findUserByPhone(lowercaseLogin)
            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        //* jwt 赋值默认角色为 ROLE_USER *//*
        Set<Authority> authorities = new HashSet<>();
        Authority auth = new Authority();
        auth.setName(AuthoritiesConstants.USER);
        authorities.add(auth);
        List<GrantedAuthority> grantedAuthorities = authorities.stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(lowercaseLogin,
            user.getPassword(),
            grantedAuthorities);
    }
}
