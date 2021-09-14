package com.springbootapplication.service;

import com.springbootapplication.dao.RoleDAO;
import com.springbootapplication.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        super(roleDAO);
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findByRole(String role) {
        return roleDAO.findByRole(role);
    }

    @Override
    public Set<Role> findAllRoles() {
        return roleDAO.findAllRoles();
    }

    @Override
    public Set<Role> rolesSetFromArray(String[] rolesArr) {
        return roleDAO.rolesSetFromArray(rolesArr);
    }

    @Override
    public Role create(Role role) {

        if(findAllRoles().contains(role)){
            System.out.println("Role " + role + " already exists");
        }

        return roleDAO.create(role);
    }
}
