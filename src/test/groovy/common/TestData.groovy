package common

import com.example.domain.helpers.CountNamePair
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

}