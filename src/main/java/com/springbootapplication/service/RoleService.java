package com.springbootapplication.service;

import com.springbootapplication.entity.Role;
import java.util.Set;

public interface RoleService extends GenericService<Role> {

    Role findByRole(String role);
    Set<Role> findAllRoles();
    Set<Role> rolesSetFromArray(String[] input);
}
