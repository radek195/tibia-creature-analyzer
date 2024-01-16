package com.example.domain.monsterstats;

import lombok.Builder;
import lombok.Getter;

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

}
