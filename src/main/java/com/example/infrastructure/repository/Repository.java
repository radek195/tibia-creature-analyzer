package com.example.infrastructure.repository;

import com.example.domain.monsterstats.MonsterStats;

import java.util.List;

public interface Repository<T> {

    long save(T object);

    T get(long id);

    List<T> getAll();

    void update(MonsterStats object, long id);

    void delete(long id);
}
