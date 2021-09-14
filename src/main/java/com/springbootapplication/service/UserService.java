package com.springbootapplication.service;

import com.springbootapplication.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends GenericService<User>, UserDetailsService {

    User findByUsername(String username);
    List<User> findAllUsers();

}

