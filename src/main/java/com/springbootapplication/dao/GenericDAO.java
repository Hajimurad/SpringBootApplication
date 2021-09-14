package com.springbootapplication.dao;

public interface GenericDAO<T> {

    T create(T entity);

    T readById(Long id);

    T update(T entity);

    void deleteById(Long id);
}
