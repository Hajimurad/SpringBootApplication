package com.springbootapplication.dao;

import com.springbootapplication.entity.Role;

import java.util.Set;

public interface RoleDAO {

    Set<Role> findAllRoles();

    Role readById(Long id);
    Role findByRole(String role);
    Role update(Role role);

    void deleteById(Long id);
}
