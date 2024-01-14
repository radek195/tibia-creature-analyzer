package com.example.domain.solohunt;

import com.example.domain.helpers.NameQuantityPair;
import lombok.Getter;

@Getter
public class SoloHunt {
    Long id;
    int balance;
    NameQuantityPair[] killedMonsters;
    int loot;
    NameQuantityPair[] lootedItems;
    int supplies;
}
