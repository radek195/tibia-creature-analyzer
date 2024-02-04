package common

import groovy.sql.Sql

import static com.example.infrastructure.dao.DbConnection.SCHEMA

class DbHelper {
    Sql db

    DbHelper() {
        db = Sql.newInstance(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "password"
        )
    }

    void cleanTables() {
        db.execute("DELETE FROM ${SCHEMA}.monster_stats" as String)
        db.execute("DELETE FROM ${SCHEMA}.solo_hunts" as String)
    }
}
