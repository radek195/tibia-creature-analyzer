package integration.looteditems

import com.example.domain.looteditems.LootedItem
import com.example.infrastructure.dao.Dao
import com.example.infrastructure.dao.DbConnection
import com.example.infrastructure.dao.looteditems.LootedItemDao
import common.TestData
import integration.IntegrationSpec

class LootedItemDaoIT extends IntegrationSpec implements TestData {

    private Dao<LootedItem> underTest = new LootedItemDao(new DbConnection())

    def "Should save looted item record"() {
        given:
            LootedItem expected = getLootedItem()
        when:
            long id = underTest.save(expected)
            def retrieved = underTest.get(id)
            def actual = retrieved.isPresent() ? retrieved.get() : null

        then:
            assertLootedItem(expected, actual)
    }

    def "Should return list of looted item records"() {
        given:
            dbHelper.cleanTables()

        and:
            LootedItem expected = getLootedItem()
            underTest.save(expected)
            underTest.save(expected)
            underTest.save(expected)

        when:
            def actual = underTest.getAll()

        then:
            actual.size() == 3
            actual.forEach { assertLootedItem(expected, it) }
    }

    def "Should update looted item record"() {
        given:
            LootedItem lootedItem = getLootedItem()
            long id = underTest.save(lootedItem)

        when:
            LootedItem expected = getUpdatedLootedItem()
            underTest.update(expected, id)

        then:
            def retrieved = underTest.get(id)
            def actual = retrieved.isPresent() ? retrieved.get() : null
            assertLootedItem(expected, actual)
    }

    def "Should delete looted item record"() {
        given:
            LootedItem lootedItem = getLootedItem()
            long id = underTest.save(lootedItem)

        when:
            underTest.delete(id)

        then:
            def retrieved = underTest.get(id)
            assert retrieved.isEmpty()
    }

    def assertLootedItem(LootedItem expected, LootedItem actual) {
        assert expected.name == actual.name
        assert expected.amount == actual.amount
        true
    }

}
