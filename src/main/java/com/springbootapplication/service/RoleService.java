package com.springbootapplication.service;

import com.springbootapplication.entity.Role;
import java.util.Set;

public interface RoleService  {

    Set<Role> findAllRoles();
    Set<Role> rolesSetFromArray(Long[] input);

    Role readById(Long id);
    Role update(Role role);
    Role findByRole(String role);

    void deleteById(Long id);
}
