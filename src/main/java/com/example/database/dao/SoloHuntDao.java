package com.example.database.dao;

import com.example.database.DbConnection;
import com.example.database.MappingHelper;
import com.example.domain.solohunt.SoloHunt;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.example.database.DbConnection.SCHEMA;

@RequiredArgsConstructor
public class SoloHuntDao implements Dao<SoloHunt> {

    private final DbConnection dbConnection;
    private final MappingHelper mappingHelper;

    @Override
    public long save(SoloHunt object) throws SQLException {
        long id = getNextSequenceValue();
        String killedMonsters = mappingHelper.from(object.getKilledMonsters());
        String lootedItems = mappingHelper.from(object.getLootedItems());
        String queryFormat = "INSERT INTO %s.solo_hunts(balance, killed_monsters, loot, looted_items, supplies) VALUES(?, '%s', ?, '%s', ?)";

        String query = String.format(queryFormat, SCHEMA, killedMonsters, lootedItems);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setInt(1, object.getBalance());
        statement.setInt(2, object.getLoot());
        statement.setInt(3, object.getSupplies());

        dbConnection.executeUpdate(statement);
        return id;
    }

    @Override
    public Optional<SoloHunt> get(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s.solo_hunts WHERE id = ?", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        ResultSet rs = dbConnection.executeQuery(statement);

        return Optional.ofNullable(rs.next() ? SoloHunt.from(rs, mappingHelper) : null);
    }

    @Override
    public void update(SoloHunt object, long id) throws SQLException {
        String killedMonsters = mappingHelper.from(object.getKilledMonsters());
        String lootedItems = mappingHelper.from(object.getLootedItems());
        String queryFormat = "UPDATE %s.solo_hunts SET balance = ?, killed_monsters = '%s', loot = ?, looted_items = '%s', supplies = ? WHERE id = ?";

        String query = String.format(queryFormat, SCHEMA, killedMonsters, lootedItems);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setInt(1, object.getBalance());
        statement.setInt(2, object.getLoot());
        statement.setInt(3, object.getSupplies());
        statement.setLong(4, id);

        dbConnection.executeUpdate(statement);
    }

    @Override
    public void delete(long id) throws SQLException {
        String query = String.format("DELETE FROM %s.solo_hunts WHERE id = ?", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        dbConnection.executeUpdate(statement);
    }

    private long getNextSequenceValue() throws SQLException {
        String query = String.format("SELECT last_value FROM %s.solo_hunts_id_seq", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);

        ResultSet rs = dbConnection.executeQuery(statement);

        if (rs.next()) {
            return rs.getLong("last_value");
        }
        throw new RuntimeException("Could not find next index value");
    }
}
