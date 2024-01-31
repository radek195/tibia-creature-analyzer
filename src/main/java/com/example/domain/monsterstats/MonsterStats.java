package com.example.domain.monsterstats;

import lombok.Builder;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Builder
public class MonsterStats {
    Long id;
    String name;
    int amountKilled;
    int avgLoot;
    int totalLoot;
    int avgBalance;
    int avgSupplies;

    public static MonsterStats from(ResultSet rs) throws SQLException {
        return MonsterStats.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .amountKilled(rs.getInt("amount_killed"))
                .avgLoot(rs.getInt("avg_loot"))
                .totalLoot(rs.getInt("total_loot"))
                .avgBalance(rs.getInt("avg_balance"))
                .avgSupplies(rs.getInt("avg_supplies"))
                .build();
    }
}
