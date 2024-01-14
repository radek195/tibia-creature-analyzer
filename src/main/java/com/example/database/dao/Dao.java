package com.example.database.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.Optional;

public interface Dao<T> {

    void save(T object) throws SQLException, JsonProcessingException;

    Optional<T> get(long id) throws SQLException, JsonProcessingException;

    void update(T object, long id) throws SQLException;

    void delete(long id) throws SQLException;
}
