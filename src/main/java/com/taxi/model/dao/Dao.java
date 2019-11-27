package com.taxi.model.dao;

import java.util.List;

public interface Dao<ID,T>  {

    void create(T entity);

    T findById(ID id);

    List<T> findAll();

    boolean update(T t);

    boolean delete(ID id);

}
