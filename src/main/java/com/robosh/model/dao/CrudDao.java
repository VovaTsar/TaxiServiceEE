package com.robosh.model.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<ID,E>  {

    void save(E entity);

    E findById(ID id);

    List<E> findAll();

    boolean update(E e);

    default boolean deleteById(ID id) {
        throw new UnsupportedOperationException();
    }

}
