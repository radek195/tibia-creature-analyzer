package integration.monsterstats

import com.example.domain.monsterstats.MonsterStats
import com.example.infrastructure.dao.Dao
import com.example.infrastructure.dao.DbConnection
import com.example.infrastructure.dao.monsterstats.MonsterStatsDao
import common.TestData
import integration.IntegrationSpec

class MonsterStatsDaoIT extends IntegrationSpec implements TestData {

    private Dao<MonsterStats> underTest = new MonsterStatsDao(new DbConnection())

    def "Should save monster stats record"() {
        given:
            MonsterStats expected = getMonsterStats()
        when:
            long id = underTest.save(expected)
            def retrieved = underTest.get(id)
            def actual = retrieved.isPresent() ? retrieved.get() : null

        then:
            assertMonsterStats(expected, actual)
    }

    def "Should return list of monster stats records"() {
        given:
            dbHelper.cleanTables()

        and:
            MonsterStats expected = getMonsterStats()
            underTest.save(expected)
            underTest.save(expected)
            underTest.save(expected)

        when:
            def actual = underTest.getAll()

        then:
            actual.size() == 3
            actual.forEach { assertMonsterStats(expected, it) }
    }

    def "Should update looted item record"() {
        given:
            MonsterStats monsterStats = getMonsterStats()
            long id = underTest.save(monsterStats)

        when:
            MonsterStats expected = getUpdatedMonsterStats()
            underTest.update(expected, id)

        then:
            def retrieved = underTest.get(id)
            def actual = retrieved.isPresent() ? retrieved.get() : null
            assertMonsterStats(expected, actual)
    }

    def "Should delete looted item record"() {
        given:
            MonsterStats monsterStats = getMonsterStats()
            long id = underTest.save(monsterStats)

        when:
            underTest.delete(id)

        then:
            def retrieved = underTest.get(id)
            assert retrieved.isEmpty()
    }

    def assertMonsterStats(MonsterStats expected, MonsterStats actual) {
        assert expected.name == actual.name
        assert expected.amountKilled == actual.amountKilled
        assert expected.avgLoot == actual.avgLoot
        assert expected.totalLoot == actual.totalLoot
        assert expected.avgBalance == actual.avgBalance
        assert expected.avgSupplies == actual.avgSupplies
        true
    }

}
