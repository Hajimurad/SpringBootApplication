package com.springbootapplication.dao;

import com.springbootapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO{

    public UserDaoImpl() {
    }

    @PersistenceContext
    private  EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
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
    public User create(User user) {
        User userCont = entityManager.merge(user);
        entityManager.persist(userCont);
        return user;
    }

    @Override
    public User readById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID does not stand for object");
        }
        return entityManager.find(User.class, id);
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Entity has not been found");
        }

        return entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID has not been found while removing object");
        }
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }


    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
}
