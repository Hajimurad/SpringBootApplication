package com.springbootapplication.dao;

import com.springbootapplication.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDAO<User> implements UserDAO{

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        super(User.class, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public User findByUsername(String username) {

        if (username == null) {
            throw new IllegalArgumentException("Username is null");
        }

        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        try {

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;
        }
    }

    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
}
