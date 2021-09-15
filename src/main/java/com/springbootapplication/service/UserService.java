package com.springbootapplication.service;

import com.springbootapplication.entity.User;
import java.util.List;

public interface UserService  {

    List<User> findAllUsers();

    User create(User user);
    User readById(Long id);
    User update(User user);
    User findByUsername(String name);

    void deleteById(Long id);
}

