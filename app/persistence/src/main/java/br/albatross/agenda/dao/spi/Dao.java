package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

public interface Dao<T, K> {

    T persist(T t);
    T merge(T t);

    boolean existsById(Class<T> entityClass, K id);

    List<T> findAll(Class<T> entityClass);

    Optional<T> findById(Class<T> entityClass, K id);
    Optional<T> getReferenceById(Class<T> entityClass, K id);

    void removeByReference(T t);

}
