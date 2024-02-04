package com.example.domain.monsterstats;

import com.example.domain.Repository;
import com.example.infrastructure.dao.Dao;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MonsterStatsRepository implements Repository<MonsterStats> {

    private final Dao<MonsterStats> monsterStatsDao;

    @Override
    public long save(MonsterStats monsterStats) {
        try {
            return monsterStatsDao.save(monsterStats);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MonsterStats get(long id) {
        try {
            Optional<MonsterStats> optionalMonsterStats = monsterStatsDao.get(id);
            if (optionalMonsterStats.isPresent()) {
                return optionalMonsterStats.get();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Could not retrieve MonsterStats object with id " + id);
    }

    @Override
    public List<MonsterStats> getAll() {
        try {
            return monsterStatsDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MonsterStats object, long id) {
        try {
            monsterStatsDao.update(object, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            monsterStatsDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
