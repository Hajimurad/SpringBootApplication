package com.springbootapplication.dao;

import com.springbootapplication.entity.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl extends AbstractDAO<Role> implements RoleDAO {

    private final EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        super(Role.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Role findByRole(String roleName) {
        try {
            entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :roleName", Role.class)
                    .setParameter("roleName", roleName).getResultList();
        } catch (NoResultException e) {
            Role role = new Role();
            role.setRole(roleName);
            entityManager.persist(role);
        }
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getResultList().stream().findAny().get();
    }

    @Override
    public Set<Role> rolesSetFromArray(String[] roleArr) {
        if (roleArr == null) {
            return null;
        }
        Set<Role> roles = new HashSet<>();
        for (String s : roleArr) {
            roles.add(findByRole(s));
        }
        return roles;
    }

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(entityManager.createQuery("FROM Role", Role.class).getResultList());
    }
}
