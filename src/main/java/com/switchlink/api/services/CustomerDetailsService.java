package com.switchlink.api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;


}
