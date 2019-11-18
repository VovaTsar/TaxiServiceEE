package com.mytaxi.model.dao;

import java.util.List;

public interface CrudDao<ID,E>  {

    void save(E entity);

    E findById(ID id);

    List<E> findAll();

    boolean update(E e);

    default boolean deleteById(ID id) {
        throw new UnsupportedOperationException();
    }

}
