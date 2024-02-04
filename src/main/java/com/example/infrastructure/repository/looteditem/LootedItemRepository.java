package com.example.infrastructure.repository.looteditem;

import com.example.domain.looteditems.LootedItem;
import com.example.infrastructure.dao.Dao;
import com.example.infrastructure.repository.Repository;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LootedItemRepository implements Repository<LootedItem> {

    private final Dao<LootedItem> lootedItemDao;

    @Override
    public long save(LootedItem lootedItem) {
        try {
            return lootedItemDao.save(lootedItem);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LootedItem get(long id) {
        try {
            Optional<LootedItem> optionalLootedItem = lootedItemDao.get(id);
            if (optionalLootedItem.isPresent()) {
                return optionalLootedItem.get();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Could not retrieve LootedItem object with id " + id);
    }

    @Override
    public List<LootedItem> getAll() {
        try {
            return lootedItemDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(LootedItem object, long id) {
        try {
            lootedItemDao.update(object, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            lootedItemDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
