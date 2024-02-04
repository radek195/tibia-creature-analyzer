package com.example.infrastructure.dao.solohunt;

import com.example.domain.solohunt.SoloHunt;
import com.example.infrastructure.MappingHelper;
import com.example.infrastructure.dao.Dao;
import com.example.infrastructure.dao.DbConnection;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SoloHuntDao implements Dao<SoloHunt> {

    private final DbConnection dbConnection;
    private final MappingHelper mappingHelper;

    @Override
    public long save(SoloHunt object) throws SQLException {
        String killedMonsters = mappingHelper.from(object.getKilledMonsters());
        String lootedItems = mappingHelper.from(object.getLootedItems());
        String queryFormat = "INSERT INTO %s.solo_hunts(balance, killed_monsters, loot, looted_items, supplies) VALUES(?, '%s', ?, '%s', ?)";

        String query = String.format(queryFormat, DbConnection.SCHEMA, killedMonsters, lootedItems);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setInt(1, object.getBalance());
        statement.setInt(2, object.getLoot());
        statement.setInt(3, object.getSupplies());

        dbConnection.executeUpdate(statement);
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        throw new RuntimeException("Could not get id of saved record.");
    }

    @Override
    public Optional<SoloHunt> get(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s.solo_hunts WHERE id = ?", DbConnection.SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        ResultSet rs = dbConnection.executeQuery(statement);
        return Optional.ofNullable(rs.next() ? SoloHunt.from(rs, mappingHelper) : null);
    }

    @Override
    public List<SoloHunt> getAll() throws SQLException {
        String query = String.format("SELECT * FROM %s.solo_hunts", DbConnection.SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);

        ResultSet rs = dbConnection.executeQuery(statement);

        List<SoloHunt> soloHuntList = new ArrayList<>();
        while (rs.next()) {
            soloHuntList.add(SoloHunt.from(rs, mappingHelper));
        }

        return soloHuntList;
    }

    @Override
    public void update(SoloHunt object, long id) throws SQLException {
        String killedMonsters = mappingHelper.from(object.getKilledMonsters());
        String lootedItems = mappingHelper.from(object.getLootedItems());
        String queryFormat = "UPDATE %s.solo_hunts SET balance = ?, killed_monsters = '%s', loot = ?, looted_items = '%s', supplies = ? WHERE id = ?";

        String query = String.format(queryFormat, DbConnection.SCHEMA, killedMonsters, lootedItems);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setInt(1, object.getBalance());
        statement.setInt(2, object.getLoot());
        statement.setInt(3, object.getSupplies());
        statement.setLong(4, id);

        dbConnection.executeUpdate(statement);
    }

    @Override
    public void delete(long id) throws SQLException {
        String query = String.format("DELETE FROM %s.solo_hunts WHERE id = ?", DbConnection.SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        dbConnection.executeUpdate(statement);
    }
}
