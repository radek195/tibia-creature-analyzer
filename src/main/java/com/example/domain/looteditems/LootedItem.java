package com.example.domain.looteditems;

import lombok.Builder;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Builder
public class LootedItem {
    Long id;
    String name;
    int amount;

    public static LootedItem from(ResultSet rs) throws SQLException {
        return LootedItem.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .amount(rs.getInt("amount"))
                .build();
    }
}
