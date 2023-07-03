package com.microservice.oauth.service;

import com.microservice.commonsusers.entity.User;
import com.microservice.oauth.client.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserFeignClient client;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = client.findByUserName(username);
        if(user == null) {
            log.error("Error en el login, no existe el usuario '"+username+"' en el sistema");
            throw new UsernameNotFoundException("Error en el login, no existe el usuario '"+username+"' en el sistema");
        }

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNameRole()))
                .peek(authority -> log.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        log.info("Usuario autenticado: " + username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),user.getEnabled(),
                true,true,true,authorities);
    }




}
