package com.example.domain.solohunt;

import com.example.domain.helpers.CountNamePair;
import com.example.infrastructure.MappingHelper;
import lombok.Builder;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
@Builder
public class SoloHunt {
    Long id;
    int balance;
    List<CountNamePair> killedMonsters;
    int loot;
    List<CountNamePair> lootedItems;
    int supplies;

    public static SoloHunt from(ResultSet rs, MappingHelper mappingHelper) throws SQLException {
        return SoloHunt.builder()
                .id(rs.getLong("id"))
                .balance(rs.getInt("balance"))
                .killedMonsters(mappingHelper.jsonToList(rs.getString("killed_monsters")))
                .loot(rs.getInt("loot"))
                .lootedItems(mappingHelper.jsonToList(rs.getString("looted_items")))
                .supplies(rs.getInt("supplies"))
                .build();
    }
}
