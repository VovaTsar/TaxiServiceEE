package my.project.model.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<ID, E> {
    boolean save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    void update(E entity);

    boolean deleteById(ID id);
}
