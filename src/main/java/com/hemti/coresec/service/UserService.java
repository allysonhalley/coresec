package com.hemti.coresec.service;

import com.hemti.coresec.model.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ResponseEntity getAllUsers();
    ResponseEntity getUserByUsername(String username);
}
