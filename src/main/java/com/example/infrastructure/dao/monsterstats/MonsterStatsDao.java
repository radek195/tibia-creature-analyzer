package com.example.infrastructure.dao.monsterstats;

import com.example.domain.monsterstats.MonsterStats;
import com.example.infrastructure.dao.Dao;
import com.example.infrastructure.dao.DbConnection;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.infrastructure.dao.DbConnection.SCHEMA;

@RequiredArgsConstructor
public class MonsterStatsDao implements Dao<MonsterStats> {

    private final DbConnection dbConnection;

    @Override
    public long save(MonsterStats object) throws SQLException {
        String query = String.format("INSERT INTO %s.monster_stats(name, amount_killed, avg_loot, total_loot, avg_balance, avg_supplies) VALUES(?, ?, ?, ?, ?, ?)", SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, object.getName());
        statement.setInt(2, object.getAmountKilled());
        statement.setInt(3, object.getAvgLoot());
        statement.setInt(4, object.getTotalLoot());
        statement.setInt(5, object.getAvgBalance());
        statement.setInt(6, object.getAvgSupplies());

        dbConnection.executeUpdate(statement);
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        throw new RuntimeException("Could not get id of saved record.");
    }

    @Override
    public Optional<MonsterStats> get(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s.monster_stats WHERE id = ?", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        ResultSet rs = dbConnection.executeQuery(statement);

        return Optional.ofNullable(rs.next() ? MonsterStats.from(rs) : null);
    }

    @Override
    public List<MonsterStats> getAll() throws SQLException {
        String query = String.format("SELECT * FROM %s.monster_stats", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);

        ResultSet rs = dbConnection.executeQuery(statement);
        List<MonsterStats> monsterStatsList = new ArrayList<>();

        while (rs.next()) {
            monsterStatsList.add(MonsterStats.from(rs));
        }

        return monsterStatsList;
    }


    @Override
    public void update(MonsterStats object, long id) throws SQLException {
        String query = String.format("UPDATE %s.monster_stats SET name = ?, amount_killed = ?, avg_loot = ?, total_loot = ?, avg_balance = ?, avg_supplies = ? WHERE id = ?", SCHEMA);
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
        String query = String.format("DELETE FROM %s.monster_stats WHERE id = ?", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        dbConnection.executeUpdate(statement);
    }
}
