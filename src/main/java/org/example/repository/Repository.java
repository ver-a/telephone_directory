package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    T save(T entity);
    void delete(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
}

/*
import java.util.List;

public interface Repository<T, Integer> {
    void add(T object);
    void removeAll();
    void update(int id, T newObject);
    T getById(int id);
    List<T> getAll();
}
*/