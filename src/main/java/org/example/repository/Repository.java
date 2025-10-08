package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    T save(T entity);
    void delete(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
}
