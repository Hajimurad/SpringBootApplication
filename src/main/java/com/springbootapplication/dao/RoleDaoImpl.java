package com.springbootapplication.dao;

import com.springbootapplication.entity.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDAO {

    public RoleDaoImpl() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByRole(String role) {

        return entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class)
                .setParameter("role", role)
                .getSingleResult();
    }

    public static <L> L getSingleResultOrNull(TypedQuery<L> query) {
        List<L> results = query.getResultList();
        L foundEntity = null;
        if(!results.isEmpty()) {
            foundEntity = results.get(0);
        }
        if(results.size() > 1) {
            for(L result : results) {
                if(result != foundEntity) {
                    throw new NonUniqueResultException();
                }
            }
        }
        return foundEntity;
    }


    @Override
    public Role readById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID does not stand for object");
        }
        return entityManager.find(Role.class, id);    }


    @Override
    public Role update(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Entity has not been found");
        }
        return entityManager.merge(role);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID has not been found while removing object");
        }
        Role entity = entityManager.find(Role.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(entityManager.createQuery("FROM Role", Role.class).getResultList());
    }
}
