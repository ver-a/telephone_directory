package org.example.service;

import java.util.List;

import java.util.Optional;

public interface Service<T, ID> {
    T create(T entity);
    void delete(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
}
/*
public interface Service<T, Integer> {
    T getById(int id);
    List<T> getAll();

    //какие методы должны реализовывать сервисы придумаете сами
}*/
