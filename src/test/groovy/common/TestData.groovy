package common

import com.example.domain.helpers.CountNamePair
import com.example.domain.looteditems.LootedItem
import com.example.domain.monsterstats.MonsterStats
import com.example.domain.solohunt.SoloHunt

trait TestData {

    SoloHunt getSoloHunt() {
        SoloHunt.builder()
                .balance(100)
                .killedMonsters(List.of(
                        new CountNamePair(59, "orc"),
                        new CountNamePair(211, "amazon"))
                )
                .loot(555)
                .lootedItems(List.of(
                        new CountNamePair(2, "shield"),
                        new CountNamePair(1553, "gold coins"))
                )
                .supplies(5998)
                .build()
    }

    SoloHunt getUpdatedSoloHunt() {
        SoloHunt.builder()
                .balance(222)
                .killedMonsters(List.of(
                        new CountNamePair(59, "orc"))
                )
                .loot(333)
                .lootedItems(List.of(
                        new CountNamePair(2, "shield"),
                        new CountNamePair(1414, "gold coins"),
                        new CountNamePair(2067, "gold coins"))
                )
                .supplies(1)
                .build()
    }

    MonsterStats getMonsterStats() {
        MonsterStats.builder()
                .name("Dragon")
                .amountKilled(15)
                .avgLoot(223)
                .totalLoot(29846)
                .avgBalance(9896)
                .avgSupplies(941)
                .build()
    }

    MonsterStats getUpdatedMonsterStats() {
        MonsterStats.builder()
                .name("Dragon")
                .amountKilled(125)
                .avgLoot(22)
                .totalLoot(26)
                .avgBalance(916)
                .avgSupplies(94112)
                .build()
    }

    LootedItem getLootedItem() {
        LootedItem.builder()
                .name("Shield")
                .amount(14)
                .build()
    }

    LootedItem getUpdatedLootedItem() {
        LootedItem.builder()
                .name("Mace")
                .amount(1119)
                .build()
    }
}