CREATE TABLE solo_hunts (
    id serial,
    balance INTEGER,
    killed_monsters json,
    loot INTEGER,
    looted_items json,
    supplies INTEGER
);

CREATE TABLE monster_stats (
    id serial,
    name VARCHAR(50),
    amount_killed INTEGER,
    avg_loot INTEGER,
    total_loot INTEGER,
    avg_balance INTEGER,
    avg_supplies INTEGER
);