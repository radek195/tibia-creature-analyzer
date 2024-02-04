package integration.solohunts

import com.example.domain.solohunt.SoloHunt
import com.example.infrastructure.MappingHelper
import com.example.infrastructure.dao.Dao
import com.example.infrastructure.dao.DbConnection
import com.example.infrastructure.dao.solohunt.SoloHuntDao
import com.fasterxml.jackson.databind.ObjectMapper
import common.TestData
import integration.IntegrationSpec

class SoloHuntDaoIT extends IntegrationSpec implements TestData {

    MappingHelper mappingHelper = new MappingHelper(new ObjectMapper())
    private Dao<SoloHunt> underTest = new SoloHuntDao(new DbConnection(), mappingHelper)

    def "Should save solo hunt record"() {
        given:
            SoloHunt expected = getSoloHunt()
        when:
            long id = underTest.save(soloHunt)
            def retrieved = underTest.get(id)
            def actual = retrieved.isPresent() ? retrieved.get() : null

        then:
            assertSoloHunt(expected, actual)
    }

    def "Should return list of solo hunt records"() {
        given:
            dbHelper.cleanTables()

        and:
            def expected = getSoloHunt()
            underTest.save(expected)
            underTest.save(expected)
            underTest.save(expected)
            underTest.save(expected)

        when:
            def actual = underTest.getAll()

        then:
            actual.size() == 4
            actual.forEach { assertSoloHunt(expected, it) }
    }

    def "Should update solo hunt record"() {
        given:
            SoloHunt soloHunt = getSoloHunt()
            long id = underTest.save(soloHunt)

        when:
            SoloHunt expected = getUpdatedSoloHunt()
            underTest.update(expected, id)

        then:
            def retrieved = underTest.get(id)
            def actual = retrieved.isPresent() ? retrieved.get() : null
            assertSoloHunt(expected, actual)
    }

    def "Should delete solo hunt record"() {
        given:
            SoloHunt soloHunt = getSoloHunt()
            long id = underTest.save(soloHunt)

        when:
            underTest.delete(id)

        then:
            def retrieved = underTest.get(id)
            assert retrieved.isEmpty()
    }

    def assertSoloHunt(SoloHunt expected, SoloHunt actual) {
        assert expected.balance == actual.balance
        assert mappingHelper.from(expected.killedMonsters) == mappingHelper.from(actual.killedMonsters)
        assert expected.loot == actual.loot
        assert mappingHelper.from(expected.lootedItems) == mappingHelper.from(actual.lootedItems)
        assert expected.supplies == actual.supplies
        true
    }

}
