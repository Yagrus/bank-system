package ru.clevertec.bank.dao;

import java.util.List;

public interface Repository<T> {

    void save(T entity);

    T findById(long id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);
}
