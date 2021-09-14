package com.springbootapplication.dao;

import com.springbootapplication.entity.Role;

import java.util.Set;

public interface RoleDAO extends GenericDAO<Role> {

    Role findByRole(String role);
    Set<Role> findAllRoles();
    Set<Role> rolesSetFromArray(String[] roleName);
}
