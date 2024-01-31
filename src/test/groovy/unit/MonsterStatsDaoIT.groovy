package unit

import com.example.database.DbConnection
import com.example.database.dao.Dao
import com.example.database.dao.MonsterStatsDao
import com.example.domain.monsterstats.MonsterStats
import common.TestData
import spock.lang.Specification

class MonsterStatsDaoIT extends Specification implements TestData {

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

    def "Should update solo hunt record"() {
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

    def "Should delete solo hunt record"() {
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
