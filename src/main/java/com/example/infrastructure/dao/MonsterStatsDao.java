package com.example.infrastructure.dao;

import com.example.domain.monsterstats.MonsterStats;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class MonsterStatsDao implements Dao<MonsterStats> {

    private final DbConnection dbConnection;

    @Override
    public long save(MonsterStats object) throws SQLException {
        long id = getNextSequenceValue();
        String query = String.format("INSERT INTO %s.monster_stats(name, amount_killed, avg_loot, total_loot, avg_balance, avg_supplies) VALUES(?, ?, ?, ?, ?, ?)", DbConnection.SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, object.getName());
        statement.setInt(2, object.getAmountKilled());
        statement.setInt(3, object.getAvgLoot());
        statement.setInt(4, object.getTotalLoot());
        statement.setInt(5, object.getAvgBalance());
        statement.setInt(6, object.getAvgSupplies());

        dbConnection.executeUpdate(statement);
        return id;
    }

    @Override
    public Optional<MonsterStats> get(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s.monster_stats WHERE id = ?", DbConnection.SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        ResultSet rs = dbConnection.executeQuery(statement);

        return Optional.ofNullable(rs.next() ? MonsterStats.from(rs) : null);
    }

    @Override
    public void update(MonsterStats object, long id) throws SQLException {
        String query = String.format("UPDATE %s.monster_stats SET name = ?, amount_killed = ?, avg_loot = ?, total_loot = ?, avg_balance = ?, avg_supplies = ? WHERE id = ?", DbConnection.SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, object.getName());
        statement.setInt(2, object.getAmountKilled());
        statement.setInt(3, object.getAvgLoot());
        statement.setInt(4, object.getTotalLoot());
        statement.setInt(5, object.getAvgBalance());
        statement.setInt(6, object.getAvgSupplies());
        statement.setLong(7, id);

        dbConnection.executeUpdate(statement);
    }

    @Override
    public void delete(long id) throws SQLException {
        String query = String.format("DELETE FROM %s.monster_stats WHERE id = ?", DbConnection.SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        dbConnection.executeUpdate(statement);
    }

    private long getNextSequenceValue() throws SQLException {
        String query = String.format("SELECT last_value FROM %s.monster_stats_id_seq", DbConnection.SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);

        ResultSet rs = dbConnection.executeQuery(statement);

        if (rs.next()) {
            return rs.getLong("last_value");
        }
        throw new RuntimeException("Could not find next index value");
    }
}
