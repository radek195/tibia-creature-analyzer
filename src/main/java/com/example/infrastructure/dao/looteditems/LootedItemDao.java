package com.example.infrastructure.dao.looteditems;

import com.example.domain.looteditems.LootedItem;
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
public class LootedItemDao implements Dao<LootedItem> {

    private final DbConnection dbConnection;

    @Override
    public long save(LootedItem lootedItem) throws SQLException {
        String query = String.format("INSERT INTO %s.looted_items(name, amount) VALUES(?, ?)",
                SCHEMA);

        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, lootedItem.getName());
        statement.setInt(2, lootedItem.getAmount());

        dbConnection.executeUpdate(statement);
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        throw new RuntimeException("Could not get id of saved record.");
    }

    @Override
    public Optional<LootedItem> get(long id) throws SQLException {
        String query = String.format("SELECT * FROM %s.looted_items WHERE id = ?", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        ResultSet rs = dbConnection.executeQuery(statement);

        return Optional.ofNullable(rs.next() ? LootedItem.from(rs) : null);
    }

    @Override
    public List<LootedItem> getAll() throws SQLException {
        String query = String.format("SELECT * FROM %s.looted_items", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);

        ResultSet rs = dbConnection.executeQuery(statement);
        List<LootedItem> lootedItemList = new ArrayList<>();

        while (rs.next()) {
            lootedItemList.add(LootedItem.from(rs));
        }

        return lootedItemList;
    }

    @Override
    public void update(LootedItem object, long id) throws SQLException {
        String query = String.format("UPDATE %s.looted_items SET name = ?, amount = ? WHERE id = ?", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setString(1, object.getName());
        statement.setInt(2, object.getAmount());
        statement.setLong(3, id);

        dbConnection.executeUpdate(statement);
    }

    @Override
    public void delete(long id) throws SQLException {
        String query = String.format("DELETE FROM %s.looted_items WHERE id = ?", SCHEMA);
        PreparedStatement statement = dbConnection.createPreparedStatement(query);
        statement.setLong(1, id);

        dbConnection.executeUpdate(statement);
    }
}
