package com.springbootapplication.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

    @PersistenceContext
    private final EntityManager entityManager;

    private final Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    @Override
    public T create(T entity) {
        T t = entityManager.merge(entity);
        entityManager.persist(t);
        return entity;
    }

    @Override
    public T readById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID does not stand for object");
        }
        return entityManager.find(entityClass, id);
    }

    @Override
    public T update(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity has not been found");
        }
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID has not been found while removing object");
        }
        T entity = entityManager.find(entityClass, id);
        entityManager.remove(entity);
    }
}
