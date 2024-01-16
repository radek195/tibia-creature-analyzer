package com.example.database.dao;

import com.example.database.DbConnection;
import com.example.domain.monsterstats.MonsterStats;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static com.example.database.DbConnection.SCHEMA;

@RequiredArgsConstructor
public class MonsterStatsDao implements Dao<MonsterStats> {

    private final DbConnection dbConnection;

    @Override
    public void save(MonsterStats object) throws SQLException {
        String query = String.format("INSERT INTO %s.monster_stats(name, amount_killed, avg_loot, total_loot, avg_balance, avg_supplies) VALUES(?, ?, ?, ?, ?, ?", SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, object.getName());
        statement.setInt(2, object.getAmountKilled());
        statement.setInt(3, object.getAvgLoot());
        statement.setInt(4, object.getTotalLoot());
        statement.setInt(5, object.getAvgBalance());
        statement.setInt(6, object.getAvgSupplies());

        dbConnection.executeUpdate(statement);
    }

    @Override
    public Optional<MonsterStats> get(long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void update(MonsterStats object, long id) throws SQLException {

    }

    @Override
    public void delete(long id) throws SQLException {

    }
}
