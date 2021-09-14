package com.springbootapplication.service;

public interface GenericService<T>  {

    T create(T entity);

    T readById(Long id);

    T update(T entity);

    void deleteById(Long id);

}
