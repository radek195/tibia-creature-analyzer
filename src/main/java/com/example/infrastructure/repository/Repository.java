package com.example.infrastructure.repository;

import java.util.List;

public interface Repository<T> {

    long save(T object);

    T get(long id);

    List<T> getAll();

    void update(T object, long id);

    void delete(long id);
}
