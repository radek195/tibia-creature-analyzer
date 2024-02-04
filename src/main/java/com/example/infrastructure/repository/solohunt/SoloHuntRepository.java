package com.example.infrastructure.repository.solohunt;

import com.example.domain.solohunt.SoloHunt;
import com.example.infrastructure.dao.Dao;
import com.example.infrastructure.repository.Repository;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SoloHuntRepository implements Repository<SoloHunt> {

    private final Dao<SoloHunt> soloHuntDao;

    @Override
    public long save(SoloHunt soloHunt) {
        try {
            return soloHuntDao.save(soloHunt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SoloHunt get(long id) {
        try {
            Optional<SoloHunt> optionalSoloHunt = soloHuntDao.get(id);
            if (optionalSoloHunt.isPresent()) {
                return optionalSoloHunt.get();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Could not retrieve SoloHunt record with id " + id);
    }

    @Override
    public List<SoloHunt> getAll() {
        try {
            return soloHuntDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(SoloHunt soloHunt, long id) {
        try {
            soloHuntDao.update(soloHunt, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            soloHuntDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
