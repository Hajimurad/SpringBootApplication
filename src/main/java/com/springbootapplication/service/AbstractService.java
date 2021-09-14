package com.springbootapplication.service;

import com.springbootapplication.dao.GenericDAO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractService<T> implements GenericService<T> {

    private final GenericDAO<T> tGenericDAO;

    public AbstractService(GenericDAO<T> tGenericDAO) {
        this.tGenericDAO = tGenericDAO;
    }

    @Override
    public T create(T entity) {
        return tGenericDAO.create(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T readById(Long id) {
        return tGenericDAO.readById(id);
    }

    @Override
    public T update(T entity) {
        return tGenericDAO.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        tGenericDAO.deleteById(id);
    }

}
