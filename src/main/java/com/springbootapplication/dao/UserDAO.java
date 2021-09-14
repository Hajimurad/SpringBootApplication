package com.springbootapplication.dao;

import com.springbootapplication.entity.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    User findByUsername(String name);
    List<User> findAllUsers();
}
