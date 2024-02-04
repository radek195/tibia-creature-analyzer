package com.example.infrastructure.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    long save(T object) throws SQLException;

    Optional<T> get(long id) throws SQLException;

    List<T> getAll() throws SQLException;

    void update(T object, long id) throws SQLException;

    void delete(long id) throws SQLException;
}
